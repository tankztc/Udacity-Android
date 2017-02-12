package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameField = (EditText) findViewById(R.id.name_field);
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.checkbox_cream);
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.checkbox_chocolate);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        boolean hasChocolate = chocolateCheckBox.isChecked();
        String name = nameField.getText().toString();
        int price = calPrice(5);
        String message = createOrderSummary(name, price, hasWhippedCream, hasChocolate);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        Log.v("MailActivity", "I'm her1e");
        if (intent.resolveActivity(getPackageManager()) != null) {
            Log.v("MailActivity", "I'm here");
            startActivity(intent);
        }
        displayMessage(message);
    }

    private String createOrderSummary(String name, int price, boolean hasWhippedCream, boolean hasChocolate) {
        String orderMessage = "Name: " + name + "\n";
        orderMessage += "Add whipped cream: " + hasWhippedCream + "\n";
        orderMessage += "Add chocolate: " + hasChocolate + "\n";
        orderMessage += "Quantity: " + quantity + "\n";
        orderMessage += "Total: $" + price + "\n";
        orderMessage += "Thank you!";
        return orderMessage;
    }

    public void increment(View view) {
        quantity += 1;
        display(quantity);
        displayPrice(calPrice(5));
    }

    public void decrement(View view) {
        quantity -= 1;
        display(quantity);
        displayPrice(calPrice(5));
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    private int calPrice(int price) {
        return price * quantity;
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderTextView = (TextView) findViewById(R.id.order_text_view);
        orderTextView.setText(message);
    }
}