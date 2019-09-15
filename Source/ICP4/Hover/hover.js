function upDate(previewPic) {

    $('#image').css('background-image', 'url(' + previewPic.src + ')');
    $("#image").text(previewPic.alt) ;


}

function unDo() {
    $('#image').css('background-image', 'url(' + "image" + ')');
    $("#image").text("image") ;
    /* In this function you should
   1) Update the url for the background image of the div with the id = "image"
   back to the orginal-image.  You can use the css code to see what that original URL was

   2) Change the text  of the div with the id = "image"
   back to the original text.  You can use the html code to see what that original text was
   */

}
