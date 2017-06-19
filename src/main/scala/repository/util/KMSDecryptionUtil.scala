package repository.util

import java.nio.ByteBuffer
import java.nio.charset.Charset

import com.amazonaws.services.kms.AWSKMSClientBuilder
import com.amazonaws.services.kms.model.DecryptRequest
import com.amazonaws.util.Base64

object KMSDecryptionUtil {

  private lazy val kmsClient = AWSKMSClientBuilder.defaultClient()

  def decrypt(encryptedValue: String): String = {
    val encrypted = Base64.decode(encryptedValue)

    val decryptedResult = kmsClient
      .decrypt(new DecryptRequest().withCiphertextBlob(ByteBuffer.wrap(encrypted)))
      .getPlaintext

    new String(decryptedResult.array(), Charset.forName("UTF-8"))
  }

}
