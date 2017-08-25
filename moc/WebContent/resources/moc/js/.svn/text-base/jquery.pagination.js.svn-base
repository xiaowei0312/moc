/**
 * This jQuery plugin displays pagination links inside the selected elements.
 * 
 * This plugin needs at least jQuery 1.4.2
 *
 * @author Gabriel Birke (birke *at* d-scribe *dot* de)
 * @version 2.0rc
 * @param {int} maxentries Number of entries to paginate
 * @param {Object} opts Several options (see README for documentation)
 * @return {Object} jQuery Object
 */
 (function($){
	/**
	 * @class Class for calculating pagination values
	 * 计算分页信息
	 */
	$.PaginationCalculator = function(maxentries, opts) {
		this.maxentries = maxentries;
		this.opts = opts;
	}
	/**
	 * 重新构造计算分页函数的原型对象
	 */
	$.extend($.PaginationCalculator.prototype, {
		/**
		 * 总共有多少页
		 * Calculate the maximum number of pages
		 * @method
		 * @returns {Number}
		 */
		numPages:function() {
			return Math.ceil(this.maxentries/this.opts.items_per_page);
		},
		/**
		 * 以当页码为中心连续显示num_display_entries-------start:end
		 * Calculate start and end point of pagination links depending on 
		 * current_page and num_display_entries.
		 * @returns {Array}
		 */
		getInterval:function(current_page)  {
			var ne_half = Math.ceil(this.opts.num_display_entries/2);
			var np = this.numPages();
			var upper_limit = np - this.opts.num_display_entries;
			var start = current_page > ne_half ? Math.max( Math.min(current_page - ne_half, upper_limit), 0 ) : 0;
			var end = current_page > ne_half?Math.min(current_page+ne_half, np):Math.min(this.opts.num_display_entries, np);
			return {start:start, end:end};
		}
	});
	
	// Initialize jQuery object container for pagination renderers----分页显示
	$.PaginationRenderers = {}
	
	/**
	 * 定义计算分页的函数为pc---包含（两个对象--函数对象和原型对象）
	 * @class Default renderer for rendering pagination links
	 */
	$.PaginationRenderers.defaultRenderer = function(maxentries, opts) {
		this.maxentries = maxentries;
		this.opts = opts;
		this.pc = new $.PaginationCalculator(maxentries, opts);
	}
	$.extend($.PaginationRenderers.defaultRenderer.prototype, {
		/**
		 * 分页渲染
		 * Helper function for generating a single link (or a span tag if it's the currentPage page)
		 * @param {Number} page_id --要跳的页面
		 * @param {Number} current_page 
		 * @param {Object} appendopts 文本和样式
		 * @returns {jQuery} jQuery object containing the link
		 */
		createLink:function(page_id, current_page, appendopts){
			var lnk, np = this.pc.numPages();
			page_id = page_id<0?0:(page_id<np?page_id:np-1); // Normalize page id to sane value
			appendopts = $.extend({text:page_id+1, classes:""}, appendopts||{});
			if(page_id == current_page){
				lnk = $("<span class='currentPage'>" + appendopts.text + "</span>");
			}
			else
			{
				lnk = $("<a>" + appendopts.text + "</a>")
					.attr('href', this.opts.link_to.replace(/__id__/,page_id));
			}
			if(appendopts.classes){ lnk.addClass(appendopts.classes).removeClass("currentPage"); }
			lnk.data('page_id', page_id);
			return lnk;
		},
		// Generate a range of numeric links 
		appendRange:function(container, current_page, start, end) {
			var i;
			for(i=start; i<end; i++) {
				this.createLink(i, current_page).appendTo(container);
			}
		},
		/**
		 * 生成分页
		 * @param current_page
		 * @param eventHandler
		 * @returns
		 */
		getLinks:function(current_page, eventHandler) {
			var begin, end,
				interval = this.pc.getInterval(current_page),
				np = this.pc.numPages(),
				fragment = $("<div class='pagination'></div>");
			//firstPage
			if(this.opts.prev_text && (current_page != np )){
				fragment.append(this.createLink(0, current_page, {text:this.opts.first_text, classes:"firstPage"}));
			}
			
			
			// Generate "Previous"-Link
			if(this.opts.prev_text && (current_page > 0 || this.opts.prev_show_always)){
				fragment.append(this.createLink(current_page-1, current_page, {text:this.opts.prev_text, classes:"previousPage"}));
			}
			// Generate starting points
			if (interval.start > 0 && this.opts.num_edge_entries > 0)
			{
				end = Math.min(this.opts.num_edge_entries, interval.start);
				this.appendRange(fragment, current_page, 0, end);
				if(this.opts.num_edge_entries < interval.start && this.opts.ellipse_text)
				{
					jQuery("<span>"+this.opts.ellipse_text+"</span>").appendTo(fragment);
				}
			}
			
			// Generate interval links
			this.appendRange(fragment, current_page, interval.start, interval.end);
			// Generate ending points
			if (interval.end < np && this.opts.num_edge_entries > 0)
			{
				if(np-this.opts.num_edge_entries > interval.end && this.opts.ellipse_text)
				{
					jQuery("<span >"+this.opts.ellipse_text+"</span>").appendTo(fragment);
				}
				begin = Math.max(np-this.opts.num_edge_entries, interval.end);
				this.appendRange(fragment, current_page, begin, np);
				
			}
			// Generate "Next"-Link
			if(this.opts.next_text && (current_page < np-1 || this.opts.next_show_always)){
				fragment.append(this.createLink(current_page+1, current_page, {text:this.opts.next_text, classes:"nextPage"}));
			}
			
			//lastPage
			if(this.opts.last_text){
				if( current_page >= interval.end-1){
					fragment.append(this.createLink(0,0, {text:this.opts.last_text, classes:"currentPage lastPage"}));
				}else{
					fragment.append(this.createLink(end,end, {text:this.opts.last_text, classes:"lastPage"}));
				}
			}
			$('a', fragment).click(eventHandler);
			return fragment;
		}
	});
	
	// Extend jQuery
	$.fn.pagination = function(maxentries, opts){
		maxentries = Number(maxentries);
		
    // Initialize options with default values
	opts = jQuery.extend({
		items_per_page:10,
		num_display_entries:10,
		current_page:0,
		num_edge_entries:0,
		link_to:"#",
		//First,Last,Prev,Next
		first_text:"&nbsp;&nbsp;",
		last_text:"&nbsp;&nbsp;",
		prev_text:"&nbsp;&nbsp;",
		next_text:"&nbsp;&nbsp;",
		ellipse_text:"...",
		prev_show_always:true,
		next_show_always:true,
		renderer:"defaultRenderer",
		callback:function(){return false;}
	},opts||{});
	
	var containers = this,
		renderer, links, current_page;
	
		
		/**
		 * This is the event handling function for the pagination links. 
		 * @param {int} page_id The new page number
		 */
		function pageSelected(evt){
			var links, current_page = $(evt.target).data('page_id');
			containers.data('current_page', current_page);
			links = renderer.getLinks(current_page, pageSelected);
			containers.empty();
			links.appendTo(containers);
			var continuePropagation = opts.callback(current_page, containers);
			if (!continuePropagation) {
				if (evt.stopPropagation) {
					evt.stopPropagation();
				}
				else {
					evt.cancelBubble = true;
				}
			}
			return continuePropagation;
		}
		
		current_page = opts.current_page;
		containers.data('current_page', current_page);
		// Create a sane value for maxentries and items_per_page
		maxentries = (!maxentries || maxentries < 0)?1:maxentries;
		opts.items_per_page = (!opts.items_per_page || opts.items_per_page < 0)?1:opts.items_per_page;
		
		if(!$.PaginationRenderers[opts.renderer])
		{
			throw new ReferenceError("Pagination renderer '" + opts.renderer + "' was not found in jQuery.PaginationRenderers object.");
		}
		renderer = new $.PaginationRenderers[opts.renderer](maxentries, opts);
		
		containers.each(function() {
		// Attach control functions to the DOM element 
		this.selectPage = function(page_id){ pageSelected(page_id);}
		this.prevPage = function(){
			var current_page = containers.data('current_page');
			if (current_page > 0) {
				pageSelected(current_page - 1);
				return true;
			}
			else {
				return false;
			}
		}
		this.nextPage = function(){
			var current_page = containers.data('current_page');
			if(current_page < numPages()-1) {
				pageSelected(current_page+1);
				return true;
			}
			else {
				return false;
			}
		}
		});
		// When all initialisation is done, draw the links
		links = renderer.getLinks(current_page, pageSelected);
		containers.empty();
		links.appendTo(containers);
		// call callback function
		opts.callback(current_page, containers);
	
}

})(jQuery);