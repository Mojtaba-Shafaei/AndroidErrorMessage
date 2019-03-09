# Android Error/Message/Loading

A customizable Android library to display Error/Message/Loading.

### Requirements

- MIN-SDK Version = 14

### Screenshots
![sample AndroidToolbarBadgeButton image](https://github.com/Mojtaba-Shafaei/AndroidErrorMessage/blob/master/screenshots/screen01.gif)

### Methods 
- `setTypeface(typeface: Typeface?)`    // set `Typeface` of error `TextView` and `Button`
- `showMessage(...)`                    // show suitable UI for a message
- `showError(...)`                      // show suitable UI for an error
- `showInternetError(...)`              // show suitable UI for an Internet not found error
- `showNoData(...)`                     // show suitable UI for Data Not Available
- `showLoading(...)`                    // show suitable UI initializing loading
- `showListLoading(...)`                // show suitable UI for initializing a list
- `hide(...)`                           // hide the library

### XML
```xml
    <com.mojtaba_shafaei.android.ErrorMessage
        android:id="@+id/em"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

```

### **Install**
[![](https://jitpack.io/v/Mojtaba-Shafaei/AndroidErrorMessage.svg)](https://jitpack.io/#Mojtaba-Shafaei/AndroidErrorMessage)