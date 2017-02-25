var gulp = require('gulp');
var runSequence = require('run-sequence');
var webpack = require('gulp-webpack');
var config = require('../config').build;

gulp.task('build', function(callback) {
    runSequence([
        'clean',
        'webpack'],
        callback
    );
});

gulp.task('webpack',function(){
    return gulp.src([config.js.src])
    .pipe(webpack(require(config.webpack)))
    .pipe(gulp.dest(config.js.dest));
});