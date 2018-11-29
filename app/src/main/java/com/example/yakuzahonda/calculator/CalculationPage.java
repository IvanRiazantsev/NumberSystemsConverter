package com.example.yakuzahonda.calculator;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;


public class CalculationPage extends Fragment implements View.OnClickListener {
    View view;
    private Integer[] numberSystemsList = {2, 3, 8, 10, 16};
    public EditText inputText;
    public EditText outputText;
    private final String WRONG_ERROR_STRING = "WRONG";
    private final String TOO_LARGE_ERROR_STRING = "Too large number";

    public CalculationPage() {
    }

    public SpannableString makeRed(String string) {
        SpannableString strSpan = new SpannableString(string);
        strSpan.setSpan(new ForegroundColorSpan(Color.RED), 0, string.length(), 0);
        return strSpan;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.calculationpage, container, false);

        ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.custom_spinner, numberSystemsList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        final Spinner spinner2 = (Spinner) view.findViewById(R.id.spinner2);
        spinner2.setAdapter(adapter);

        //swap
        ImageButton swapButton = (ImageButton) view.findViewById(R.id.swapButton);
        swapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int spinner1Index = spinner.getSelectedItemPosition();

                spinner.setSelection(spinner2.getSelectedItemPosition());
                spinner2.setSelection(spinner1Index);
            }
        });


        //dictionary
        final Map<Integer, Integer> valuesMap = new HashMap<>();
        valuesMap.put(0, 2);
        valuesMap.put(1, 3);
        valuesMap.put(2, 8);
        valuesMap.put(3, 10);
        valuesMap.put(4, 16);

        //editTextViews
        inputText = (EditText) view.findViewById(R.id.inputEditText);
        outputText = (EditText) view.findViewById(R.id.inputEditText2);


        //paste
        Button pasteButton = (Button) view.findViewById(R.id.pasteButton);
        pasteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                try {
                    CharSequence toPaste = clipboardManager.getPrimaryClip().getItemAt(0).coerceToText(getContext());
                    inputText.setText(toPaste);
                } catch (NullPointerException e) {
                    Toast.makeText(getContext(), "Nothing to paste", Toast.LENGTH_SHORT).show();
                }
            }
        });


        //convert
        final Button convertButton = (Button) view.findViewById(R.id.calculateButton);
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!inputText.getText().toString().equals("")) {
                    int init = spinner.getSelectedItemPosition();
                    init = valuesMap.get(init); //initial numeral system

                    int target = spinner2.getSelectedItemPosition();
                    target = valuesMap.get(target); //target numeral system

                    String input = inputText.getText().toString();

                    if ((init == 2 && !input.matches("[01]+")) || (init == 3 && !input.matches("[0-2]+"))
                            || (init == 8 && !input.matches("[0-7]+")) || (init == 10 && !input.matches("[0-9]+"))) {
                        outputText.setText(makeRed(WRONG_ERROR_STRING), TextView.BufferType.EDITABLE);
                    } else {
                        try {
                            Long decimal = Long.valueOf(input, init);//to decimal
                            if (target == 10) {
                                outputText.setText(decimal.toString().toUpperCase(), TextView.BufferType.EDITABLE);
                            } else {
                                String resultString = Long.toString(decimal, target);
                                outputText.setText(resultString.toUpperCase(), TextView.BufferType.EDITABLE);
                            }
                        } catch (NumberFormatException e) {

                            outputText.setText(makeRed(TOO_LARGE_ERROR_STRING), TextView.BufferType.EDITABLE);
                        }
                    }
                }
            }
        });

        //copyButton
        Button copyButton = (Button) view.findViewById(R.id.copyButton);
        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String copied = outputText.getText().toString();
                ClipboardManager clipboardManager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("result", copied);
                clipboardManager.setPrimaryClip(clipData);

                Toast.makeText(getContext(), "Copied!", Toast.LENGTH_SHORT).show();
            }
        });

        //cleanButton
        Button cleanButton = (Button) view.findViewById(R.id.cleanButton);
        cleanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputText.setText("");
                outputText.setText("");
            }
        });


        //keyboardButtons
        Button button0 = (Button) view.findViewById(R.id.button0);
        button0.setOnClickListener(this);

        Button button1 = (Button) view.findViewById(R.id.button1);
        button1.setOnClickListener(this);

        Button button2 = (Button) view.findViewById(R.id.button2);
        button2.setOnClickListener(this);

        Button button3 = (Button) view.findViewById(R.id.button3);
        button3.setOnClickListener(this);

        Button button4 = (Button) view.findViewById(R.id.button4);
        button4.setOnClickListener(this);

        Button button5 = (Button) view.findViewById(R.id.button5);
        button5.setOnClickListener(this);

        Button button6 = (Button) view.findViewById(R.id.button6);
        button6.setOnClickListener(this);

        Button button7 = (Button) view.findViewById(R.id.button7);
        button7.setOnClickListener(this);

        Button button8 = (Button) view.findViewById(R.id.button8);
        button8.setOnClickListener(this);

        Button button9 = (Button) view.findViewById(R.id.button9);
        button9.setOnClickListener(this);

        Button buttonA = (Button) view.findViewById(R.id.buttonA);
        buttonA.setOnClickListener(this);

        Button buttonB = (Button) view.findViewById(R.id.buttonB);
        buttonB.setOnClickListener(this);

        Button buttonC = (Button) view.findViewById(R.id.buttonC);
        buttonC.setOnClickListener(this);

        Button buttonD = (Button) view.findViewById(R.id.buttonD);
        buttonD.setOnClickListener(this);

        Button buttonE = (Button) view.findViewById(R.id.buttonE);
        buttonE.setOnClickListener(this);

        Button buttonF = (Button) view.findViewById(R.id.buttonF);
        buttonF.setOnClickListener(this);

        Button delButton = (Button) view.findViewById(R.id.delButton);
        delButton.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button0:
                inputText.setText(inputText.getText() + "0");
                break;
            case R.id.button1:
                inputText.setText(inputText.getText() + "1");
                break;
            case R.id.button2:
                inputText.setText(inputText.getText() + "2");
                break;
            case R.id.button3:
                inputText.setText(inputText.getText() + "3");
                break;
            case R.id.button4:
                inputText.setText(inputText.getText() + "4");
                break;
            case R.id.button5:
                inputText.setText(inputText.getText() + "5");
                break;
            case R.id.button6:
                inputText.setText(inputText.getText() + "6");
                break;
            case R.id.button7:
                inputText.setText(inputText.getText() + "7");
                break;
            case R.id.button8:
                inputText.setText(inputText.getText() + "8");
                break;
            case R.id.button9:
                inputText.setText(inputText.getText() + "9");
                break;
            case R.id.buttonA:
                inputText.setText(inputText.getText() + "A");
                break;
            case R.id.buttonB:
                inputText.setText(inputText.getText() + "B");
                break;
            case R.id.buttonC:
                inputText.setText(inputText.getText() + "C");
                break;
            case R.id.buttonD:
                inputText.setText(inputText.getText() + "D");
                break;
            case R.id.buttonE:
                inputText.setText(inputText.getText() + "E");
                break;
            case R.id.buttonF:
                inputText.setText(inputText.getText() + "F");
                break;
            case R.id.delButton:
                if (!inputText.getText().toString().equals("")) {
                    inputText.setText(inputText.getText().subSequence(0, inputText.getText().length() - 1));
                }
                break;
        }
    }
}
