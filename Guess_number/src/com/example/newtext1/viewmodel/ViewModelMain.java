package com.example.newtext1.viewmodel;

import java.util.ArrayList;

import com.example.newtext1.model.ModelNumber;

public class ViewModelMain {
	public ArrayList<ModelNumber> model_list;
	public int A;
	public int B;

	public void inputToModel(String user_input) {
		if (model_list != null) {
			model_list.clear();
		}

		int user_input_length = user_input.length();
		for (int i = 0; i < user_input_length; i++) {
			int number = Integer.parseInt(user_input.substring(i, i + 1));
//			int numberans = Integer.parseInt(answer.substring(i, i + 1));
			ModelNumber model = new ModelNumber();
			model.number = number;
			model.position = i;
//			model.numberans = numberans;
//			model.positionans = i;

			model_list.add(model);
		}
	}

	public void judgeAorB(String answer) {
		for (ModelNumber model : model_list) {
			for (int i = 0; i < answer.length(); i++) {
				int answer_number = Integer
						.parseInt(answer.substring(i, i + 1));
				if (model.number == answer_number) {
					model.is_number_correct = true;
					if (model.position == i) {
						model.is_position_correct = true;
						
					}
				}
			}
		}
	}

//	public void judgeAorB(String answer) {
//		for (ModelNumber model : model_list) {
//
//			if ((model.number == model.numberans)
//					& (model.position == model.positionans)) {
//				model.is_position_correct = true;
//			} else if (model.number == model.numberans) {
//				model.is_number_correct = true;
//			}
//		}
//	}

	public void getABCount() {
		for (ModelNumber model : model_list) {
			if (model.is_number_correct) {
				if (model.is_position_correct) {
					A++;
				} else {
					B++;
				}
			}
		}
	}

	public void initAB() {
		A = 0;
		B = 0;
	}

	
}
