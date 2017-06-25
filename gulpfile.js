'use strict';

const concat = require('gulp-concat');
const del = require('del');
const eslint = require('gulp-eslint');
const gulp = require('gulp');
const gutil = require('gulp-util');
const path = require('path');
const runSequence = require('run-sequence');
const sass = require('gulp-sass');
const webpack = require('webpack-stream');

const DIST_DIR = './dist';
const SRC_DIR = './';

gulp.task('clean', () => {
  return del([`${DIST_DIR}/**/*`]);
});

gulp.task('sass', () => {
  return gulp.src(`${SRC_DIR}/public/**/*.scss`)
    .pipe(sass({outputStyle: 'compressed'})
    .on('error', sass.logError))
    .pipe(concat('main.min.css'))
    .pipe(gulp.dest(`${DIST_DIR}/css`));
});

gulp.task('webpack', () => {
  return gulp.src(`${SRC_DIR}/app/main.jsx`)
    .pipe(webpack(require('./webpack.config.js')))
    .pipe(gulp.dest(`${DIST_DIR}/js`));
});

gulp.task('build', cb => {
  runSequence(
    'clean',
    'sass',
    'webpack',
    cb
  );
});

gulp.task('watch', ['build'], () => {
  const fileChanged = file => {
    let filePath = path.relative(__dirname, file.path);
  };

  gulp.watch(
    [`${SRC_DIR}/public/**/*.scss`],
    ['sass']
  ).on('change', fileChanged);

  gulp.watch(
    [`${SRC_DIR}/app/**/*.jsx`],
    ['webpack']
  ).on('change', fileChanged);
});

gulp.task('default', ['build']);