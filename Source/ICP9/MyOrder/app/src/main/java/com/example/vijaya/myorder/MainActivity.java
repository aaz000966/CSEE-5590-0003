package com.example.vijaya.myorder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String MAIN_ACTIVITY_TAG = "MainActivity";
    final int PizzaPrice = 5;
    final int Cheese = 1;
    final int BlackOlive =1;
    final int thin =2;
    final int thick =4;

    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sumBtn =(Button)findViewById(R.id.buttonSum);
        Button sndBtn =(Button)findViewById(R.id.buttonSend);


        sumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitOrder(view);
            }
        });
        sndBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });

    }

    /**
     * This method is called when the order button is clicked.
     */

    public void submitOrder(View view) {

        // get user input
        EditText userInputNameView = (EditText) findViewById(R.id.user_input);
        String userInputName = userInputNameView.getText().toString();

        // check if whipped cream is selected
        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream_checked);
        boolean hasWhippedCream = whippedCream.isChecked();

        // check if chocolate is selected
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_checked);
        boolean hasChocolate = chocolate.isChecked();

        RadioButton rb1 =(RadioButton)findViewById(R.id.radioButton);
        RadioButton rb2 =(RadioButton)findViewById(R.id.radioButton2);

        boolean isThin = (rb1.isChecked()) ? true : false;


        // calculate and store the total price
        float totalPrice = calculatePrice(hasWhippedCream, hasChocolate,isThin);



        String orderSummaryMessage = createOrderSummary(userInputName, hasWhippedCream, hasChocolate,isThin, totalPrice);

        // Write the relevant code for making the buttons work(i.e implement the implicit and explicit intents
        Intent intent = new Intent(this, Main2Activity.class);
         intent.putExtra("message",orderSummaryMessage);
        startActivity(intent);


    }
//    String name, String output
    public void sendEmail() {
        // Write the relevant code for triggering email

        // Hint to accomplish the task


        // get user input
        EditText userInputNameView = (EditText) findViewById(R.id.user_input);
        String userInputName = userInputNameView.getText().toString();

        // check if whipped cream is selected
        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream_checked);
        boolean hasWhippedCream = whippedCream.isChecked();

        // check if chocolate is selected
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_checked);
        boolean hasChocolate = chocolate.isChecked();
        RadioButton rb1 =(RadioButton)findViewById(R.id.radioButton);

        boolean isThin = (rb1.isChecked()) ? true : false;

        // calculate and store the total price
        float totalPrice = calculatePrice(hasWhippedCream, hasChocolate,isThin);

        // create and store the order summary



        String orderSummaryMessage = createOrderSummary(userInputName, hasWhippedCream, hasChocolate,isThin,totalPrice);

        // Write the relevant code for making the buttons work(i.e implement the implicit and explicit intents
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT,orderSummaryMessage);
        intent.setType("text/plain");
        if (intent.resolveActivity(getPackageManager()) !=null){
            startActivity(intent);

        }

    }

    private String boolToString(boolean bool) {
        return bool ? (getString(R.string.yes)) : (getString(R.string.no));
    }

    private String createOrderSummary(String userInputName, boolean hasWhippedCream, boolean hasChocolate,boolean isThin, float price) {

        if (isThin){

        }
        String orderSummaryMessage = getString(R.string.order_summary_name, userInputName) + "\n" +
                getString(R.string.order_summary_whipped_cream, boolToString(hasWhippedCream)) + "\n" +
                getString(R.string.order_summary_chocolate, boolToString(hasChocolate)) + "\n" +
                getString(R.string.order_summary_crust, boolToString(isThin)) + "\n" +
                getString(R.string.order_summary_quantity, quantity) + "\n" +
                getString(R.string.order_summary_total_price, price) + "\n" +
                getString(R.string.thank_you);
        return orderSummaryMessage;
    }

    /**
     * Method to calculate the total price
     *
     * @return total Price
     */
    private float calculatePrice(boolean hasWhippedCream, boolean hasChocolate, boolean isThin) {
        int basePrice = PizzaPrice;
        if (hasWhippedCream) {
            basePrice += Cheese;
        }
        if (hasChocolate) {
            basePrice += BlackOlive;
        }
        if (isThin){
            basePrice+= thin;
        }
        if (!isThin) {
            basePrice+= thick;
        }
        return quantity * basePrice;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method increments the quantity of coffee cups by one
     *
     * @param view on passes the view that we are working with to the method
     */

    public void increment(View view) {
        if (quantity < 100) {
            quantity = quantity + 1;
            display(quantity);
        } else {
            Log.i("MainActivity", "Please select less than one hundred cups of coffee");
            Context context = getApplicationContext();
            String lowerLimitToast = getString(R.string.too_much_coffee);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, lowerLimitToast, duration);
            toast.show();
            return;
        }
    }

    /**
     * This method decrements the quantity of coffee cups by one
     *
     * @param view passes on the view that we are working with to the method
     */
    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
            display(quantity);
        } else {
            Log.i("MainActivity", "Please select atleast one cup of coffee");
            Context context = getApplicationContext();
            String upperLimitToast = getString(R.string.too_little_coffee);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, upperLimitToast, duration);
            toast.show();
            return;
        }
    }
}