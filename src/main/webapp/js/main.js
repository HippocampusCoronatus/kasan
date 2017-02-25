/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};

/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {

/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId])
/******/ 			return installedModules[moduleId].exports;

/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			exports: {},
/******/ 			id: moduleId,
/******/ 			loaded: false
/******/ 		};

/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);

/******/ 		// Flag the module as loaded
/******/ 		module.loaded = true;

/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}


/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;

/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;

/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";

/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(0);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */
/***/ function(module, exports) {

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

/***/ }
/******/ ]);