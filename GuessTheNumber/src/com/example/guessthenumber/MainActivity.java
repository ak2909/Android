package com.example.guessthenumber;

import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	Random rand = new Random();
	private int correctAnswer = rand.nextInt(100) + 1;

	private int guesses = 0;
	private int mode = 0;
	private int range = 100;
	private int hintclick = 0;
	private int hintup = 0;
	private int hintdown = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button submit = (Button) findViewById(R.id.bsubmit);
		final Button hint = (Button) findViewById(R.id.bhint);
		Button r100 = (Button) findViewById(R.id.r100);
		Button r1000 = (Button) findViewById(R.id.r1000);
		Button r10000 = (Button) findViewById(R.id.r10000);
		final EditText userEntry = (EditText) findViewById(R.id.numberbox);
		final TextView top = (TextView) findViewById(R.id.topmsg);
		final TextView guess = (TextView) findViewById(R.id.totguesses);

		hint.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (hintclick == 0) {
					hintclick = 1;
					if (mode == 0) {

						if (correctAnswer % 10 != 0) {
							hintup = correctAnswer - correctAnswer % 10;
							hintdown = hintup + 10;
							top.setText("Answer is between " + hintup + " and "
									+ hintdown);
						} else {
							top.setText("Answer is between "
									+ (correctAnswer - 10) + " and "
									+ correctAnswer);
						}
					}
					if (mode == 1) {
						if (correctAnswer % 100 != 0) {
							hintup = correctAnswer - correctAnswer % 100;
							hintdown = hintup + 100;
							top.setText("Answer is between " + hintup + " and "
									+ hintdown);

						} else {
							top.setText("Answer is between "
									+ (correctAnswer - 100) + " and "
									+ correctAnswer);
						}
					}
					if (mode == 2) {
						if (correctAnswer % 1000 != 0) {
							hintup = correctAnswer - correctAnswer % 1000;
							hintdown = hintup + 1000;
							top.setText("Answer is between " + hintup + " and "
									+ hintdown);

						} else {
							top.setText("Answer is between "
									+ (correctAnswer - 1000) + " and "
									+ correctAnswer);
						}
					}

				} else {
					top.setText("Hint already used!\nAnswer is between "
							+ hintup + " and " + hintdown);
				}
			}
		});

		r100.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mode == 0) {
					top.setText("Range already 100");
				} else {
					range = 100;
					hintclick = 0;
					mode = 0;
					guesses = 0;
					userEntry.setText("");
					guess.setText("Total guesses = " + guesses);
					correctAnswer = rand.nextInt(range) + 1;
					top.setText("NEW 100 GAME");
				}
			}
		});
		r1000.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mode == 1) {
					top.setText("Range already 1000");
				} else {
					range = 1000;
					hintclick = 0;
					mode = 1;
					guesses = 0;
					userEntry.setText("");
					guess.setText("Total guesses = " + guesses);
					correctAnswer = rand.nextInt(range) + 1;
					top.setText("NEW 1000 GAME");
				}
			}
		});
		r10000.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mode == 2) {
					top.setText("Range already 10000");
				} else {
					range = 10000;
					userEntry.setText("");
					mode = 2;
					guesses = 0;
					hintclick = 0;
					guess.setText("Total guesses = " + guesses);
					correctAnswer = rand.nextInt(range) + 1;
					top.setText("NEW 10000 GAME");
				}
			}
		});

		submit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (userEntry.getText().toString().compareTo("") != 0) {
					int userNum = Integer.parseInt(userEntry.getText()
							.toString());
					if (userNum > range || userNum < 0) {
						top.setText("Enter a number between 1 and " + range);
					} else {
						if (userNum == correctAnswer) {
							guesses++;
							top.setText("YEAH! You got it right, in " + guesses
									+ " guesses, new " + range
									+ " game started");

							guesses = 0;
							guess.setText("Total guesses = " + guesses);
							correctAnswer = rand.nextInt(range) + 1;
							hintclick = 0;

						} else if (userNum > correctAnswer) {
							guesses++;
							guess.setText("Total guesses = " + guesses);
							top.setText("Sorry,Your guess is too high");
						} else if (userNum < correctAnswer) {
							guesses++;
							guess.setText("Total guesses = " + guesses);
							top.setText("Sorry,Your guess is too low");
						}
					}
				} else {
					top.setText("Enter a number!");
				}

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
