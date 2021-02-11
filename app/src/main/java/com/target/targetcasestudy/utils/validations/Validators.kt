package com.target.targetcasestudy.data

/**
 * For an explanation of how to validate credit card numbers read:
 *
 * https://www.freeformatter.com/credit-card-number-generator-validator.html#fakeNumbers
 *
 * This contains a breakdown of how this algorithm should work as
 * well as a way to generate fake credit card numbers for testing
 *
 * The structure and signature of this is open to modification, however
 * it *must* include a method, field, etc that returns a [Boolean]
 * indicating if the input is valid or not
 *
 * Additional notes:
 *  * This method does not need to validate the credit card issuer
 *  * This method must validate card number length (13 - 19 digits), but does not
 *    need to validate the length based on the issuer.
 *
 * @param creditCardNumber - credit card number of (13, 19) digits
 * @return true if a credit card number is believed to be valid,
 * otherwise false
 */
fun validateCreditCard(creditCardNumber: String): Boolean {
  var len = creditCardNumber.length
  if(len>16||len<13){
    return false
  }
  var lastDiigt = creditCardNumber[len-1].toString().toInt()
  var a = IntArray(creditCardNumber.length)
  var sum = 0
  for(i in (len-2) downTo 0){
    if(i%2==0){
      a[len-i-2] = creditCardNumber[i].toString().toInt()*2
    }
    else{
      a[len-i-2] = creditCardNumber[i].toString().toInt()
    }
    if(a[len-i-2]>9){
      a[len-i-2]-=9
    }
    sum+=a[len-i-2]
  }
  if(sum%10!=lastDiigt){
    return false
  }
  return true
}