
# SpeechlyText

* Speech to Text Android Application

![webp net-gifmaker 3](https://user-images.githubusercontent.com/11635523/40846415-2d5c8414-657f-11e8-9447-f89d4a0401aa.gif)

#### Dependency
```gradle
 implementation 'com.android.support:design:27.1.1'
```
#### Permission
**app/manifests/AndroidManifest.xml**

```xml
  <uses-permission android:name="android.permission.RECORD_AUDIO"/>
```

#### Colors

**app/res/values/colors.xml**

```xml

<color name="colorPrimary">#673AB7</color>
<color name="colorPrimaryDark">#512DA8</color>
<color name="colorAccent">#607D8B</color>
<color name="colorText">#D1C4E9</color>
<color name="colorSecondaryText">#FFFFFF</color>

```

#### Styling/Theme

**app/res/values/styles.xml**

```xml

<!--Custom Theme with no ActionBar-->
 <style name="AppThemefull" parent="Theme.AppCompat.Light.NoActionBar">
     <item name="colorPrimary">@color/colorPrimary</item>
     <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
     <item name="colorAccent">@color/colorAccent</item>
     <item name = "android:windowActionBar">false</item>
     <item name = "android:windowNoTitle">true</item>
 </style>

```

#### Dimensions
**app/res/values/dimens.xml**

```xml

<?xml version="1.0" encoding="utf-8"?>
<resources>
    <dimen name="buttonHeight">80dp</dimen>
    <dimen name="buttonWidth">80dp</dimen>
</resources>

```

## References
[Androstock](https://www.androstock.com/tutorials/android-speech-to-text-converter-android-studio.html)

[RecognizerIntent](https://developer.android.com/reference/android/speech/RecognizerIntent)

[Programcreek](https://www.programcreek.com/java-api-examples/?api=android.speech.RecognizerIntent)

