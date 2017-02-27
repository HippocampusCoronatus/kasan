// TODO:とりあえずAjax通信したかっただけ。
var kasan = {};
(function(kasan) {
	var ContextRoot = "./";
	kasan.get = function(url, params, options) {
		return kasan.send(url, null, params, options, "GET");
	};
	kasan.post = function(url, data, params, options) {
		return kasan.send(url, data, params, options, "POST");
	};
	kasan.send = function(url, data, params, options, method) {
		return new Promise(function(resolve, reject) {
			var _req = new XMLHttpRequest();
			// TODO:パラメータの付与とか？
			var _url = ContextRoot + url;
			// TODO:オプションの設定とか？
			// とりあえず非同期固定。
			_req.open(method, _url, true);
			_req.onload = function() {
				resolve(_req.responseText);
			};
			_req.onerror = function() {
				reject(_req.responseText);
			};
			_req.send(data);
		});
	};
})(kasan);