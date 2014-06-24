package com.example.newtext1;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newtext1.model.ModelNumber;
import com.example.newtext1.viewmodel.ViewModelMain;

public class mainactivity extends Activity {
	private EditText edit_user_input;
	private TextView text_answer;
	private TextView text_notify_number_digit;
	private LinearLayout layout_display;
	private ViewModelMain view_model;
	
	private int count = 0;
	private TextView answer_text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainlayout);
		view_model = new ViewModelMain();
		view_model.model_list = new ArrayList<ModelNumber>();
		
		findView();
	}

	private void findView() {
		edit_user_input = (EditText) findViewById(R.id.edit_user_input);
		text_answer = (TextView) findViewById(R.id.text_answer);
		text_notify_number_digit = (TextView) findViewById(R.id.text_notify_number_digit);
		layout_display = (LinearLayout) findViewById(R.id.layout_display);
	}

	private void answer_text(int top_limit) {
		Random c = new Random();
		int answertext = (c.nextInt(top_limit));
		if (top_limit == 1000 && answertext >= 99) {
			text_answer.setText(String.valueOf(answertext));
			text_notify_number_digit.setText(getString(R.string.number_3_digit));
		} else if (top_limit == 10000 && answertext >= 999) {
			text_answer.setText(String.valueOf(answertext));
			text_notify_number_digit.setText(getString(R.string.number_4_digit));
		} else if (top_limit == 100000 && answertext >= 9999) {
			text_answer.setText(String.valueOf(answertext));
			text_notify_number_digit.setText(getString(R.string.number_5_digit));
		}
	}

	public void create_3_number(View view) {
		answer_text(1000);
	}

	public void create_4_number(View view) {
		answer_text(10000);
	}

	public void create_5_number(View view) {
		answer_text(100000);
	}

	public void check(View view) {
		String user_input = edit_user_input.getText().toString();
		int user_input_length = user_input.length();
		int finalanswer_length = text_answer.length();
		if (user_input_length == 0) {
			Toast.makeText(this, R.string.please_input_numbers, Toast.LENGTH_SHORT).show();
			return;
		}
		else if (user_input_length != finalanswer_length) {
			Toast.makeText(this, R.string.string_error, Toast.LENGTH_SHORT).show();
			return;
		}
		String answer = text_answer.getText().toString();
		count += 1;
		view_model.initAB();
		view_model.inputToModel(user_input);
		view_model.judgeAorB(answer);
		view_model.getABCount();
		
		String a_b_count = view_model.A + "A" + view_model.B + "B";
		String result = (user_input + "   " + a_b_count);

		if (view_model.A == user_input_length) {
			String data = "";

			if (count == 1) {
				data = "你是作弊喔!!";
				count = 0;
			} else if (count < 4) {
				data = "太神啦!!";
				count = 0;
			} else if (count <= 9) {
				data = "不錯呦!!";
				count = 0;
			} else if (count > 9) {
				data = "加油~!!";
				count = 0;
			}

			AlertDialog dialog = new AlertDialog.Builder(this)
			.setTitle("猜數字")
			.setMessage(data)
			.setPositiveButton("重新",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
							layout_display.removeAllViews();
						}
					})
			.create();
			dialog.show();
		}
		
		addResultTextView(result);
		
	}

	private void addResultTextView(String result) {
		TextView check_result = new TextView(this);
		check_result.setText(result);
		// LinearLayout

		check_result.setLayoutParams(new LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		
		layout_display.addView(check_result);
	}

	public void clear_input(View view) {
		layout_display.removeAllViews();
	}
}
