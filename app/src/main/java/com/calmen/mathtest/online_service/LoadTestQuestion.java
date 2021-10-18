package com.calmen.mathtest.online_service;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.calmen.mathtest.R;
import com.calmen.mathtest.answer_mode.FragmentAnswerInputManual;
import com.calmen.mathtest.answer_mode.FragmentSelectionA;
import com.calmen.mathtest.answer_mode.FragmentSelectionB;
import com.calmen.mathtest.answer_mode.FragmentSelectionC;
import com.calmen.mathtest.answer_mode.FragmentSelectionDefault;
import com.calmen.mathtest.models.Student;
import com.calmen.mathtest.models.StudentList;
import com.calmen.mathtest.timer.CountdownTimer;
import com.calmen.mathtest.view_list.ViewStudentDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

public class LoadTestQuestion extends AppCompatActivity {

    public static final String BASE_URL = "https://10.0.2.2:8000/testwebservice/rest";
    private static final String TAG = "LoadTestQuestion";
    public static final int NUM_ITEMS = 4; // The server returns only 4 items for the question
    // Indexes for the server request type
    public static final int INDEX_FOR_QUESTIONS = 0;
    public static final int INDEX_FOR_RESULT = 1;
    public static final int INDEX_FOR_OPTIONS = 2;
    public static final int INDEX_FOR_TIME_TO_SOLVE = 3;
    public static final int OPTIONS_PER_FRAGMENT = 4;
    // Index for fragment
    public static final int FRAGMENT_A_INDEX = 0;
    public static final int FRAGMENT_B_INDEX = 1;
    public static final int FRAGMENT_C_INDEX = 2;

    private static int fragmentSize = -1;
    public static int index = 0;    // index used to switch between fragment

    // These are the options for each fragment, only 4 options will be displayed per fragment
    ArrayList<String> optionsA = new ArrayList<>();
    ArrayList<String> optionsB = new ArrayList<>();
    ArrayList<String> optionsC = new ArrayList<>();

    FragmentManager fm = getSupportFragmentManager();
    FragmentTransaction transaction = fm.beginTransaction();

    Button endTest, prev, next, submit;
    TextView question, countdownView, answer;
    ArrayList<String> options;
    CountDownTimer countDownTimer;
    private String timeLeftText;
    private String correctAnswer;
    private int score = 0;
    private long timeLeftInMilliSeconds;
    private int timeVal = 0;
    private int totalTimeSpent = 0;
    private int timeSpentPerQuestion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_test_question);

        endTest = findViewById(R.id.endTestBtn);
        prev = findViewById(R.id.prevOptBtn);
        next = findViewById(R.id.nextOptBtn);
        submit = findViewById(R.id.submitManualBtn);
        countdownView = findViewById(R.id.timeCountdownView);
        question = findViewById(R.id.questionView);
        answer = findViewById(R.id.chosenAnswerView);

        Student student = (Student) getIntent().getSerializableExtra("Student");

        // Initial state is invisible unless there are options for next and prev
        prev.setVisibility(View.INVISIBLE);
        next.setVisibility(View.INVISIBLE);

        transaction.setReorderingAllowed(true); // allow replacing of a fragment to another fragment
        new DownloaderTask().execute();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countDownTimer.cancel();
                if (answer.getText().toString().equals("")) {
                    Toast.makeText(LoadTestQuestion.this, "Answer is not chosen yet!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    // reset the condition for fragmentSize if the current options uses multiple fragment
                    // as the option for next question may be
                    // INPUT MANUALLY or OPTIONS THAT ONLY FIT FOR ONE FRAGMENT
                    if (fragmentSize != -1) {
                        fragmentSize = -1;
                    }

                    if (correctAnswer.equals(answer.getText())) {
                        score += 10;
                    } else {
                        score -= 5;
                    }
                    System.out.println("Current Mark: " + score);

                    answer.setText(""); // reset it
                    totalTimeSpent += timeVal - Integer.parseInt(timeLeftText);
                    new DownloaderTask().execute();
                }
            }
        });

        endTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /***
                 * Date formatter code reference to the code written by
                 * @Miciurash
                 * at https://stackoverflow.com/questions/8654990/how-can-i-get-current-date-in-android/15698784
                 */
                // format for date
                Date date = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                String formattedDate = df.format(date);

                // format for time
                String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

                int latestScore = score + student.getScore();
                String latestTimeSpent = "";
                System.out.println("Student: " + student.getDate());
                System.out.println("getTimeSpent: " + student.getTimeSpent());

                if (student.getTimeSpent().equals("")) {
                    latestTimeSpent += totalTimeSpent;
                } else {
                    latestTimeSpent = String.valueOf(totalTimeSpent + Integer.parseInt(student.getTimeSpent()));
                }
                Student updateStudent = new Student(student.getFirstname(), student.getLastname(), student.getId(),
                        formattedDate, latestScore, currentTime, latestTimeSpent, student.getImage(),
                        student.getEmailList(), student.getPhoneNumberList());

                StudentList studentList = new StudentList();
                try {
                    studentList.updateStudentTestResult(view.getContext(), updateStudent);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // View the result of the test
                Intent intent = new Intent(view.getContext(), ViewStudentDetails.class);
                intent.putExtra("Date", updateStudent.getDate());
                intent.putExtra("Score", updateStudent.getScore());
                intent.putExtra("Time", updateStudent.getTime());
                intent.putExtra("TimeSpent", updateStudent.getTimeSpent());
                view.getContext().startActivity(intent);

                ((Activity) view.getContext()).finish();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: NOTICE that we need to take care of the fragment size here
                //       for size of 2, then check the index == FRAGMENT_A_INDEX and index < 2

                // fragmentSize is > -1 if the options won't fit for one fragment
                if (fragmentSize != -1) {

                    // then load using iteration
                    if (index == FRAGMENT_A_INDEX) {
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragmentContainerView, FragmentSelectionB.class, null);
                        transaction.addToBackStack(null);
                        ++index;

                        // show previous in Fragment B
                        prev.setVisibility(View.VISIBLE);
                    } else if (index == FRAGMENT_B_INDEX) {
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragmentContainerView, FragmentSelectionC.class, null);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        ++index;

                        // Hide next on FragmentC and show Previous in FragmentC
                        next.setVisibility(View.INVISIBLE);
                        prev.setVisibility(View.VISIBLE);
                    } else if (index == FRAGMENT_C_INDEX) {
                        next.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index == FRAGMENT_A_INDEX) {
                    prev.setVisibility(View.INVISIBLE);
                } else if (index == FRAGMENT_B_INDEX) {
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragmentContainerView, FragmentSelectionA.class, null);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    --index;
                    // Hide previous on FragmentA and show next on FragmentA
                    prev.setVisibility(View.INVISIBLE);
                    next.setVisibility(View.VISIBLE);
                } else if (index == FRAGMENT_C_INDEX) {
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragmentContainerView, FragmentSelectionB.class, null);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    --index;

                    // show next in Fragment B
                    next.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private class DownloaderTask extends AsyncTask<Void, Integer, String> {
        private int totalBytes;

        @Override
        protected String doInBackground(Void... voids) {
            String text;
            try {
                String urlString = Uri.parse(BASE_URL)
                        .buildUpon()
                        .appendQueryParameter("method", "thedata.getit")
                        .appendQueryParameter("format", "json")
                        .appendQueryParameter("api_key", "01189998819991197253")
                        .build().toString();
                URL url = new URL(urlString);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                DownloadUtils.addCertificate(LoadTestQuestion.this, (HttpsURLConnection) connection);

                totalBytes = connection.getContentLength();

                try {
                    Log.d(TAG, "Connecting");

                    int responseCode = connection.getResponseCode();
                    String responsMSG = connection.getResponseMessage();
                    if (responseCode != HttpsURLConnection.HTTP_OK) {
                        String msg = String.format(
                                "Internal error from server: %d - %s",
                                responseCode, responsMSG
                        );
                        Log.d(TAG, msg);
                        throw new IOException(msg);
                    }
                    InputStream is = connection.getInputStream();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();

                    int bytesRead = 0;
                    byte[] buffer = new byte[1024];
                    bytesRead = is.read(buffer);
                    while (bytesRead > 0) {
                        baos.write(buffer, 0, bytesRead);
                        bytesRead = is.read(buffer);
                        // publishProgress(totalBytesRead);
                    }
                    baos.close();
                    return new String(baos.toByteArray());
                } finally {
                    Log.d(TAG, "Disconnect");
                    connection.disconnect();
                }

            } catch (GeneralSecurityException |IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            /*
            progressBar.setIndeterminate(false);
            progressBar.setMax(totalBytes);
            progressBar.setProgress(values[0]);
             */
        }

        @Override
        protected void onPostExecute(String result) {
            String[] items = parseResult(result);

            question.setText(items[0]);
            correctAnswer = items[1];

            if (items[2].equals("")) {
                // User input answer using manual input if there is no option(s)
                activateManualInput();
            } else {
                // Use option selected answer if there is more than one option
                activateOptionInput(items);
            }

            timeVal = Integer.parseInt(items[3]);
            // TESTING
            // timeVal = 20;
            timeLeftInMilliSeconds = timeVal * 1000;
            startTimer();
        }

        public void startTimer() {
            countDownTimer = new CountDownTimer(timeLeftInMilliSeconds, 1000) {
                @Override
                public void onTick(long l) {
                    timeLeftInMilliSeconds = l;
                    updateTimer();
                }

                @Override
                public void onFinish() {
                    System.out.println("Current Mark: " + score);
                    totalTimeSpent += timeVal - Integer.parseInt(timeLeftText);
                    new DownloaderTask().execute();
                    countDownTimer.cancel();
                }
            }.start();
        }

        public void updateTimer() {
            int seconds = (int) timeLeftInMilliSeconds % 600000 / 1000;

            timeLeftText = "";

            timeLeftText += seconds;
            countdownView.setText(timeLeftText);
        }

        /***
         * @param result is in the form of JSON
         * @return items that consists of all the attributes returned in JSON
         */
        private String[] parseResult(String result) {
            System.out.println("result: " + result);
            String[] items = new String[NUM_ITEMS];

            try {
                JSONObject jBase = new JSONObject(result);
                for (int i = 0; i < NUM_ITEMS; ++i) {
                    switch (i) {
                        case INDEX_FOR_QUESTIONS:
                            items[i] = jBase.getString("question");
                            break;
                        case INDEX_FOR_RESULT:
                            items[i] = jBase.getString("result");
                            break;
                        case INDEX_FOR_OPTIONS:
                            JSONArray optionsArr = jBase.getJSONArray("options");
                            // if it is not manual input, then the options length is not 0
                            if (optionsArr.length() == 0) {
                                items[i] = "";
                            } else {
                                // Initialise the entry first so that we will not have null at the beginning
                                items[i] = "";
                                for (int ii = 0; ii < optionsArr.length(); ++ii) {
                                    items[i] += optionsArr.getString(ii) + ",";
                                }
                            }
                            break;
                        case INDEX_FOR_TIME_TO_SOLVE:
                            items[i] = jBase.getString("timetosolve");
                            break;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return items;
        }

        /***
         * Change the answer mode as user manual input mode
         */
        public void activateManualInput() {
            next.setVisibility(View.INVISIBLE);
            prev.setVisibility(View.INVISIBLE);
            try {
                FragmentAnswerInputManual fragmentAnswerInputManual = (FragmentAnswerInputManual)
                        fm.findFragmentById(R.id.fragmentContainerView);

                if (fragmentAnswerInputManual == null) {
                    fragmentAnswerInputManual = new FragmentAnswerInputManual();
                    transaction.add(R.id.fragmentContainerView, fragmentAnswerInputManual).commit();

                }
            } catch (Exception e) {
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainerView, FragmentAnswerInputManual.class, null);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        }

        private ArrayList<String> splitList(ArrayList<String> options, int start) {
            ArrayList<String> newOptions = new ArrayList<>();
            int i = 0;
            while (start < options.size() && i < OPTIONS_PER_FRAGMENT) {
                newOptions.add(options.get(start));
                ++start;
                ++i;
            }
            return newOptions;
        }

        /***
         * Change the display mode using with displaying options
         */
        private void activateOptionInput(String[] items) {
            String[] optionsArr = items[2].split(",");
            options = new ArrayList<>(Arrays.asList(optionsArr));

            // One fragment will fit for options less than 4
            if (options.size() < 4) {
                next.setVisibility(View.INVISIBLE);
                prev.setVisibility(View.INVISIBLE);
                try {
                    FragmentSelectionDefault fragment = (FragmentSelectionDefault)
                            fm.findFragmentById(R.id.fragmentContainerView);
                    if (fragment == null) {
                        fragment = new FragmentSelectionDefault();
                        transaction.add(R.id.fragmentContainerView, fragment).commit();
                    }
                } catch (Exception e) {
                    try {
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragmentContainerView, FragmentSelectionDefault.class, null);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            } else {    // Fragment with NEXT and PREV for options
                next.setVisibility(View.VISIBLE);
                if (options.size() >= 1 && options.size() <= 8) {
                    optionsA = splitList(options, 0);
                    optionsB = splitList(options, 4);
                    fragmentSize = 2;   // use in controlling next and prev button
                } else {
                    optionsA = splitList(options, 0);
                    optionsB = splitList(options, 4);
                    optionsC = splitList(options, 8);
                    fragmentSize = 3;   // use in controlling next and prev button
                }

                // Load the transaction for the first time
                // then will be navigate using NEXT and PREV in the onCreate function
                try {
                    FragmentSelectionA fragmentSelectionA = (FragmentSelectionA)
                            fm.findFragmentById(R.id.fragmentContainerView);
                    if (fragmentSelectionA == null) {
                        fragmentSelectionA = new FragmentSelectionA();
                        transaction.add(R.id.fragmentContainerView, fragmentSelectionA).commit();
                    }
                } catch (Exception e) {
                    // Check if the transaction was occupied by others
                    try {
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragmentContainerView, FragmentSelectionA.class, null);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }

    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public ArrayList<String> getOptionsA() {
        return optionsA;
    }

    public ArrayList<String> getOptionsB() {
        return optionsB;
    }

    public ArrayList<String> getOptionsC() {
        return optionsC;
    }

    public TextView getAnswer() {
        return answer;
    }
}