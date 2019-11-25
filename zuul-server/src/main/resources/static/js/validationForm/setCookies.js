function setCookies(){
  var myHeader = Response.getResponseHeader(Authorization);
       var token =  Headers.arguments("Authorization");
  Cookies.set('token', myHeader, { expires: 7 })
}