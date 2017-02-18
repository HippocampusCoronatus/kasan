// TODO:とりあえずAjax通信したかっただけ。
var kasan = {};
(function(kasan) {
	var ContextRoot = "./";
	kasan.get = function(url, params, options) {
		return new Promise(function(resolve, reject) {
			var _req = new XMLHttpRequest();
			// TODO:パラメータの付与とか？
			var _url = ContextRoot + url;
			// TODO:オプションの設定とか？
			// とりあえず非同期固定。
			_req.open("GET", _url, true);
			_req.onload = function() {
				resolve(_req.responseText);
			};
			_req.onerror = function() {
				reject(_req.responseText);
			};
			_req.send(null);
		});
	};
})(kasan);