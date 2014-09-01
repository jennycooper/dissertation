$(document).on("ready", function(){
	$(".nav").find(".active").removeClass("active"); //remove active class
	var path = window.location.pathname; //getting path from url
	var linkName = $("a[href='"+path+"']");
	$(".nav").find($(linkName)).parent().addClass("active"); // update the link's colour


	var Validate = (function(name){
	            return {
	                isFormEmpty: function(query){
	                    if (query.length==0) {
	                        throw new Error ("Enter the search query!");
	                    }
	                }
	            }
	}())

	$("input.form-control").on("focus",function(){
		console.log($(this).attr("placeholder"));
		$(this).css("border" ,"1px solid #66afe9")
				$(this).css("box-shadow", "inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6)")
	});

	$("input.form-control").on("blur",function(){
		console.log($(this).attr("placeholder"));
		$(this).css("border" ,"none")
				$(this).css("box-shadow", "inset 0 0 0 rgba(0,0,0,0),0 0 8px rgba(0,0,0, 0)")
	})

	$("button.btn.search").on("click", function(e){
		if($(this).text()!="Create Indices"){
			var currentForm = $(this).closest($("form"))[0];
			var query = $(currentForm).find("input").val();
			var placeholder = $(currentForm).find("input").placeholder;
			// console.log(placeholder);
			try{
			Validate.isFormEmpty(query);
			}catch(err){
				e.preventDefault();
				$(currentForm).find("input").attr("placeholder",err.message);
				$(currentForm).find("input").css("border","1px solid #f00")
				$(currentForm).find("input")
				.css("box-shadow", "inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(247, 5, 101, 0.6)")
			}
		}
	})
})
