package com.pgkhang.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText mInputCurrencyEditText;
    private TextView mOutputCurrencyTextView;
    private Spinner mInputCurrencySpinner;
    private Spinner mOutputCurrencySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpCurrencySpinners();

        mOutputCurrencyTextView = (TextView) findViewById(R.id.output_currency_text_view);

        mInputCurrencyEditText = (EditText) findViewById(R.id.input_currency_edit_text);
        mInputCurrencyEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    mOutputCurrencyTextView.setText("");
                } else {
                    calculateOutputCurrency();
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
    }

    private void setUpCurrencySpinners() {
        mInputCurrencySpinner = (Spinner) findViewById(R.id.input_currency_spinner);
        mOutputCurrencySpinner = (Spinner) findViewById(R.id.output_currency_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.currency_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mInputCurrencySpinner.setAdapter(adapter);
        mOutputCurrencySpinner.setAdapter(adapter);
        mOutputCurrencySpinner.setSelection(adapter.getPosition("VND - Vietnamese Dong"));
        mInputCurrencySpinner.setOnItemSelectedListener(this);
        mOutputCurrencySpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) { }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        calculateOutputCurrency();
    }

    private void calculateOutputCurrency() {
        if (TextUtils.isEmpty(mInputCurrencyEditText.getText().toString())) {
            return;
        }
        Double inputMoney = Double.parseDouble(mInputCurrencyEditText.getText().toString());
        String inputCurrency = mInputCurrencySpinner.getSelectedItem().toString().substring(0, 3);
        String outputCurrency = mOutputCurrencySpinner.getSelectedItem().toString().substring(0, 3);
        Double inputCurrencyRate = ConvertRates.rates.get(inputCurrency);
        Double outputCurrencyRate = ConvertRates.rates.get(outputCurrency);
        Double outputMoney = inputMoney / inputCurrencyRate * outputCurrencyRate;
        mOutputCurrencyTextView.setText(new DecimalFormat("#,###.##").format(outputMoney));
    }
}


