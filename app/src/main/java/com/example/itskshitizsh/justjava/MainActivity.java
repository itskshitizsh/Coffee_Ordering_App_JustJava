package com.example.itskshitizsh.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int numberOfCoffees = 1; /*Initial Quantity*/
    int pricePerQuantity = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
    *  This method is called when the order button is clicked.
    * */
    public void submitOrder(View view){

       /* display(numberOfCoffees);
        displayPrice(numberOfCoffees*pricePerQuantity);*/
        CheckBox checkWhippedCream = findViewById(R.id.whipped_cream_check_box);
        boolean hasWhipped = checkWhippedCream.isChecked();
        CheckBox checkChocolate = findViewById(R.id.chocolate_check_box);
        boolean hasChocolate = checkChocolate.isChecked();
        EditText nameEditView = findViewById(R.id.name_edit_view);
        String name = nameEditView.getText().toString();
        if(name.isEmpty()){
            nameEditView.setError("Name is Required");
            nameEditView.requestFocus();
            return;
        }
        displayMessage(createOrderSummary(hasWhipped,hasChocolate,name),name);
    }

    /**
     *  this method is called to calculated price.
     * @param hasWhipped,hasChocolate
     * @return price
     */
    private double calPrice(boolean hasWhipped,boolean hasChocolate){
        int price = pricePerQuantity;
        if(hasWhipped){
            price+=1;
        }
        if(hasChocolate){
            price+=2;
        }
        return  numberOfCoffees*price;      // Total Price
    }

    public String createOrderSummary(boolean hasWhippedChecked, boolean hasChocolate,String name){
        return "Name : "+name+"\nAdd Whipped Cream ? " + hasWhippedChecked+
                "\nAdd Chocolate ? " + hasChocolate
                + "\n"+"Quantity : " +numberOfCoffees+"\nTotal : $"+calPrice(hasWhippedChecked,hasChocolate)+"\nThank You !";
    }
    /**
     * this displayMessage method display message on screen in TextView.
     * @param priceMessage is message to display on screen.
     */
    public void displayMessage(String priceMessage,String name){
//      TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
//      priceTextView.setText(priceMessage);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String subject = "Just Java order of " + name;
        intent.setData(Uri.parse("mailto:?subject=" + subject + "&body=" + priceMessage));
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }


    }
    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view){
        if(numberOfCoffees<100)
            display(++numberOfCoffees);
        else
        {
            Toast.makeText(getApplicationContext(),"Maximum Number is 99.\n More will be Dangerous (-_-)", Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view){

        if(numberOfCoffees>1)
        display(--numberOfCoffees);
        else
        {
            Toast.makeText(getApplicationContext(),"Minimum Number is 1.", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     *  This method displays the given quantity value on the screen.
     */
    private void display(int number){
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText(""+number);
    }
    /*
     * This method displays the given quantity value on the screen.
     */
/*    private void displayPrice(int number){
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
*/

}
