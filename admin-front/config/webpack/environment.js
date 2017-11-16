const { environment } = require('@rails/webpacker')

environment.loaders.set('typescript', {
  test: /\.ts$/,
  loader: 'ts-loader',
  options: {
    appendTsSuffixTo: [/\.vue$/]
  }
})

module.exports = environment
