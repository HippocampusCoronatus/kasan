var path = {
    sourceDir: 'src/main/webapp/'
};

var build = {
    js: {
        src: path.sourceDir + 'js/**/*.js',
        dest: path.sourceDir + 'js/'
    },
    // buildタスクからの位置関係を指定する
    webpack: '../../webpack.config.js'
};

var clean = {
    target: path.sourceDir + 'js/main.js'
};

module.exports = {
    build: build,
    clean: clean
};