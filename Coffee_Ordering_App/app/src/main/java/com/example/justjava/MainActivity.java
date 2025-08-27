/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.justjava;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox)findViewById(R.id.whipped_cream_chechbox);
        CheckBox chocolateCheckBox = (CheckBox)findViewById(R.id.chocolate_chechbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        boolean hasChocolate = chocolateCheckBox.isChecked();
        EditText txtname = (EditText)findViewById(R.id.name_edit_text);
        String name = txtname.getText().toString();
        int price = calculatePrice(hasWhippedCream,hasChocolate);
        String priceMessage = createOrderSummary(price,hasWhippedCream,hasChocolate,name);
      //  priceMessage = priceMessage + "\nThank You!";
      //  displayMessage(priceMessage);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for" + name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void increment(View view) {
        if (quantity<100){
            quantity++;
        }else{
            Toast.makeText(this, "You cannot have more than 100 coffees", 2000).show();
        }
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if (quantity>1){
            quantity--;
        }
        else{
            Toast.makeText(this, "You cannot have less than 1 coffee", 2000).show();
        }
        displayQuantity(quantity);
    }
    /**
     * This method displays the given text on the screen.
     */
   /* private void displayMessage(String message) {
        TextView OrderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        OrderSummaryTextView.setText(message);
    }*/
    private int calculatePrice(boolean addWhippedCream , boolean addChocolate){
       int basePrice = 5;
       if (addWhippedCream){
           basePrice = basePrice + 1 ;
       }
       if (addChocolate){
           basePrice = basePrice + 2 ;
       }
        return quantity*basePrice;
    }
    private String createOrderSummary(int price, boolean addWhippedCream ,boolean addChocolate, String name){
        String summary = "Name: " + name;
        summary+="\nAdd Whipped Cream? " + addWhippedCream;
        summary+="\nAdd Cholocate? "+ addChocolate;
        summary+="\nQuantity: "+ quantity ;
        summary+="\nTotal: $" + price ;
        summary+="\nThank you!";
        return summary;
    }
}