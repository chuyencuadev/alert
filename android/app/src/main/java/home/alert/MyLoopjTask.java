package home.alert;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MyLoopjTask {
    AsyncHttpClient asyncHttpClient;
    RequestParams requestParams;

    String jsonResponse;
    Context context;
    OnLoopjCompleted loopjListener;

    public MyLoopjTask(Context context, OnLoopjCompleted listener) {
        asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.setMaxRetriesAndTimeout(0, 1000);
        requestParams = new RequestParams();
        this.context = context;
        this.loopjListener = listener;
    }

    public void executeLoopjCall(String url) {
        asyncHttpClient.get(url, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                loopjListener.taskStart();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                jsonResponse = response.toString();
                loopjListener.taskSuccess(jsonResponse);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                loopjListener.taskFailed();
            }
            @Override
            public void onFinish() {
                loopjListener.taskCompleted();
            }
        });
    }
}