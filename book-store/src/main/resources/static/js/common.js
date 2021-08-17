//this will execute on page load(to be more specific when document ready event occurs)
if ($('.ty-compact-list').length > 5) {
	$('.ty-compact-list:gt(4)').hide();
	$('.show-more').show();
}

$('.show-more').on('click', function() {
	//toggle elements with class .ty-compact-list that their index is bigger than 2
	$('.ty-compact-list:gt(4)').toggle();
	//change text of show more element just for demonstration purposes to this demo
	//$(this).text() === 'Show more' ? $(this).text('Show less') : $(this).text('Show more');
});

// END

//this will execute on page load(to be more specific when document ready event occurs)
if ($('.ty-compact-listAuthor').length > 5) {
	$('.ty-compact-listAuthor:gt(4)').hide();
	$('.show-moreAuthor').show();
}

$('.show-moreAuthor').on('click', function() {
	//toggle elements with class .ty-compact-list that their index is bigger than 2
	$('.ty-compact-listAuthor:gt(4)').toggle();
	//change text of show more element just for demonstration purposes to this demo
	//$(this).text() === 'Show more' ? $(this).text('Show less') : $(this).text('Show more');
});

// END

//this will execute on page load(to be more specific when document ready event occurs)
if ($('.ty-compact-listNXB').length > 5) {
	$('.ty-compact-listNXB:gt(4)').hide();
	$('.show-moreAuthor').show();
}

$('.show-moreNXB').on('click', function() {
	//toggle elements with class .ty-compact-list that their index is bigger than 2
	$('.ty-compact-listNXB:gt(4)').toggle();
	//change text of show more element just for demonstration purposes to this demo
	//$(this).text() === 'Show more' ? $(this).text('Show less') : $(this).text('Show more');
});

// END

//this will execute on page load(to be more specific when document ready event occurs)
if ($('.ty-compact-listPopular').length > 5) {
	$('.ty-compact-listPopular:gt(4)').hide();
	$('.show-morePopular').show();
}

$('.show-listPopular').on('click', function() {
	//toggle elements with class .ty-compact-list that their index is bigger than 2
	$('.ty-compact-listPopular:gt(4)').toggle();
	//change text of show more element just for demonstration purposes to this demo
	//$(this).text() === 'Show more' ? $(this).text('Show less') : $(this).text('Show more');
});