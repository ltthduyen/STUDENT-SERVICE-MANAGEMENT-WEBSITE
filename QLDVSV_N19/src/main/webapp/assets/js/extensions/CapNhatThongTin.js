document.addEventListener("DOMContentLoaded", function () {
    var sdtInput = document.getElementById("sdt");
    var emailInput = document.getElementById("email");
    var dcInput = document.getElementById("dc");
    
    var submitButton = document.getElementById("submitButton");
  
   
    function enableSubmitButton() {
      submitButton.disabled = false;
    }
  
    function disableSubmitButton() {
      submitButton.disabled = true;
    }
  
    
  
    function validateSignupForm() {
        var form = document.getElementById('signupForm');
  
        for(var i=0; i < form.elements.length; i++){
            if(form.elements[i].value === '' && form.elements[i].hasAttribute('required')){
              console.log('There are some required fields!');
              return false;
            }
          }
        
        if (!validatePassword()) {
          return false;
        }
        
        onSignup();
    }
  
    function validateReturn() {
      onSignup();
    }
  
    function onSignup() {
      // Your AJAX submission logic here
      // Use sdt, email, and dc as needed in your AJAX request
  
      var xhttp = new XMLHttpRequest();
      xhttp.onreadystatechange = function () {
        disableSubmitButton();
  
        if (this.readyState == 4 && this.status == 200) {
          enableSubmitButton();
        } else {
          console.log('AJAX call failed!');
          setTimeout(function () {
            enableSubmitButton();
          }, 1000);
        }
      };
  
      // Adjust the URL and request method accordingly
      xhttp.open("POST", "your_server_endpoint", true);
      xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
      xhttp.send("sdt=" + sdt + "&email=" + email + "&dc=" + dc);
    }
  
    // Add event listeners
    submitButton.addEventListener("click", validateSignupForm);
    document.getElementById("cancel").addEventListener("click", validateReturn);
  });
  