package michael.example.com.inventoryapp.data;

import android.net.Uri;
import android.provider.BaseColumns;

public class ProductInventoryContract {
    public static final String PATH_PREFIX = "michael.example.com.inventoryapp";
    public static final Uri URI_PREFIX = Uri.parse("content://" + PATH_PREFIX);
    public static final String RESOURCE = "products";

    public static final class ProductItem implements BaseColumns {

        public static final Uri FULL_URI = Uri.withAppendedPath(URI_PREFIX, RESOURCE);

        public final static String TABLE_NAME = "products";
        public final static String PRODUCT_NAME = "product_name";
        public final static String PRICE = "price";
        public final static String QUANTITY = "quantity";
        public final static String SUPPLIER_NAME = "supplier_name";
        public final static String SUPPLIER_PHONE_NUMBER = "supplier_phone_number";
    }
}
