package com.salazarisaiahnoel.monay.fragments;

import static com.salazarisaiahnoel.monay.activities.LoginRegister.registerFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.isaiahnoelsalazar.simplefunctions.Check;
import com.github.isaiahnoelsalazar.simplefunctions.Convert;
import com.github.isaiahnoelsalazar.simplefunctions.EasySQL;
import com.salazarisaiahnoel.monay.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RegisterFragment extends Fragment {

    EditText firstname, lastname, email, password;
    Button registerbtn;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        requireActivity().getWindow().setStatusBarColor(requireContext().getColor(R.color.yellow));

        firstname = view.findViewById(R.id.firstnameregister);
        lastname = view.findViewById(R.id.lastnameregister);
        email = view.findViewById(R.id.emailregister);
        password = view.findViewById(R.id.passwordregister);
        registerbtn = view.findViewById(R.id.registerbtn);

        Check.Email.addValidDomainName("gmail");
        Check.Email.addValidDomainName("yahoo");
        Check.Email.addValidDomainName("outlook");
        Check.Email.addValidDomainExtensions("com");

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EasySQL easySQL = new EasySQL(requireContext());
                if (!easySQL.doesTableExist("account_creds_db", "account_creds_table")){
                    easySQL.createTable("account_creds_db", "account_creds_table", new String[]{"fname:text", "lname:text", "user_email:text", "user_password:text"});
                }
                List<String> emails = new ArrayList<>();
                for (String s : easySQL.getTableValues("account_creds_db", "account_creds_table")){
                    if (s.split(":")[0].equals("user_email")){
                        emails.add(s.split(":")[1].substring(1, s.split(":")[1].length() - 1));
                    }
                }
                if (emails.contains(email.getText().toString().toLowerCase())){
                    Toast.makeText(requireContext(), "This email address is already in use.", Toast.LENGTH_SHORT).show();
                } else {
                    if (Check.Email.isValid(email.getText().toString().toLowerCase())){
                        if (!easySQL.doesTableExist("user_details_db", "user_details_table")){
                            easySQL.createTable("user_details_db", "user_details_table", new String[]{"user_email:text", "money:float"});
                        }
                        if (!easySQL.doesTableExist(email.getText().toString().toLowerCase().replace("@", "").replace(".", "") + "_inbox_db", email.getText().toString().toLowerCase().replace("@", "").replace(".", "") + "_inbox_table")){
                            easySQL.createTable(email.getText().toString().toLowerCase().replace("@", "").replace(".", "") + "_inbox_db", email.getText().toString().toLowerCase().replace("@", "").replace(".", "") + "_inbox_table", new String[]{"inbox_title:text", "inbox_content:float", "date_sent:text"});
                        }
                        if (!easySQL.doesTableExist(email.getText().toString().toLowerCase().replace("@", "").replace(".", "") + "_transaction_db", email.getText().toString().toLowerCase().replace("@", "").replace(".", "") + "_transaction_table")){
                            easySQL.createTable(email.getText().toString().toLowerCase().replace("@", "").replace(".", "") + "_transaction_db", email.getText().toString().toLowerCase().replace("@", "").replace(".", "") + "_transaction_table", new String[]{"transaction_title:text", "transaction_content:float", "date_sent:text"});
                        }
                        easySQL.insertToTable("account_creds_db", "account_creds_table", new String[]{"fname:" + firstname.getText().toString(), "lname:" + lastname.getText().toString(), "user_email:" + email.getText().toString().toLowerCase(), "user_password:" + password.getText().toString()});
                        easySQL.insertToTable("user_details_db", "user_details_table", new String[]{"user_email:" + email.getText().toString().toLowerCase(), "money:0"});
                        easySQL.insertToTable(email.getText().toString().toLowerCase().replace("@", "").replace(".", "") + "_inbox_db", email.getText().toString().toLowerCase().replace("@", "").replace(".", "") + "_inbox_table", new String[]{"inbox_title:Notice", "inbox_content:Secure your account! Set a custom PIN in Profile > Settings.", "date_sent:" + Convert.dateToMMDDYY(new Date())});
                        easySQL.insertToTable(email.getText().toString().toLowerCase().replace("@", "").replace(".", "") + "_inbox_db", email.getText().toString().toLowerCase().replace("@", "").replace(".", "") + "_inbox_table", new String[]{"inbox_title:Hi!", "inbox_content:Welcome to Monay! We are pleased to have you in our platform. Enjoy first-timer benefits by adding your email address in Profile > Settings", "date_sent:" + Convert.dateToMMDDYY(new Date())});

                        // for transaction table
                        //easySQL.insertToTable(email.getText().toString().toLowerCase().replace("@", "") + "_transaction_db", email.getText().toString().toLowerCase().replace("@", "") + "_transaction_table", new String[]{"transaction_title:Hi!", "transaction_content:Welcome to Monay! We are pleased to have you in our platform. Enjoy first-timer benefits by adding your email address in Profile > Settings", "date_sent:" + Convert.dateToMMDDYY(new Date())});
                        Toast.makeText(requireContext(), "Account created successfully.", Toast.LENGTH_SHORT).show();
                        requireActivity().getSupportFragmentManager().beginTransaction().remove(registerFragment).commit();
                    } else {
                        email.setError("Please enter a valid email.");
                    }
                }
            }
        });

        TextView loginhint = view.findViewById(R.id.loginhint);
        SpannableString spannableString = new SpannableString("Log in here");
        spannableString.setSpan(new UnderlineSpan(), 0, spannableString.length(), 0);
        loginhint.setText(spannableString);
        loginhint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().beginTransaction().remove(registerFragment).commit();
            }
        });
    }
}