package View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import controller.Controller;

public class StartView {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String inputName;

            do {
                System.out.print("성함이 어떻게 되시나요? ");
                inputName = br.readLine().trim();
            } while (inputName.isBlank());

            Controller.setName(inputName);

        } catch (IOException e) {
            FailView.print("입력 오류가 발생했습니다. 프로그램을 종료합니다.");
        }
    }
}
