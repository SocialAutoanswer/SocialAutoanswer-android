package ru.bibaboba.feature_contacts

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContract

class AvatarContract: ActivityResultContract<Any, Uri?>() {

    override fun createIntent(context: Context, input: Any) = Intent(Intent.ACTION_PICK).apply {
        type = "image/*"
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Uri? = intent?.data


}