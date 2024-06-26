$(function() {
	$('#add-product-button').on("click", function() {
		$('#add-product-form').show();
	});
	$('#add-product-cancel-button').on("click", function() {
		$("#add-product-form").hide();
	});
})