package michael.example.com.inventoryapp.data;

import android.provider.BaseColumns;

public class ProductInventoryContract {
    public static final class ProductItem implements BaseColumns {
        public final static String TABLE_NAME = "products";
        public final static String PRODUCT_NAME = "product_name";
        public final static String PRICE = "price";
        public final static String QUANTITY = "quantity";
        public final static String SUPPLIER_NAME = "supplier_name";
        public final static String SUPPLIER_PHONE_NUMBER = "supplier_phone_number";
    }
}
