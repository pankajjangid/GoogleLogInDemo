# GoogleLogInDemo
Android Google Sign In authentication

Hi guys, today we are going to look at how you can simply allow users to sign in to your app via Google sign in API. It is pretty straight forward when working this this API, but sometimes there can be issues that developers face when integrating.

Lets get started.

app level gradle

    implementation 'com.squareup.picasso:picasso:2.71828'
    implementation 'com.google.android.gms:play-services-auth:15.0.1'
    implementation 'com.google.firebase:firebase-core:15.0.2'

Also it is important to apply the Google service api to the app level gradle. At the end of the file, add the following code.

apply plugin: 'com.google.gms.google-services'

When you insert the com.google.android.gms.common.SignInButton you might notice that it turns red, that is because we need to include a Google services JSON file provide by Firebase api console.
