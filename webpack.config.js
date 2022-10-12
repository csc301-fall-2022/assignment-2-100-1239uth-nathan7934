// This script configures our module bundler.
// I chose Webpack since it is the most commonly used.

const path = require('path');
const HTMLWebpackPlugin = require('html-webpack-plugin');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');

module.exports = {
    entry: './src/index.tsx',
    output: {
        path: path.join(__dirname, '/dist'), // Specify the bundled output directory
        filename: 'bundle.js'
    },

    // We use this to include the Webpack bundled output script in our specified HTML file
    plugins: [
        new HTMLWebpackPlugin({
            template: './src/index.html'
        }),
        new MiniCssExtractPlugin({
            filename: "style.css",
            chunkFilename: "style.css"
        })
    ],

    // We tell webpack to transpile all files with ext '.js' that aren't modules
    // Then, we specify Babel as our transpiler with presets for React
    // We also add a rule to tell webpack how to load css files properly for TailwindCSS
    module: {
        rules: [
            {
                test: /.js$/,
                exclude: /node_modules/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: ['@babel/preset-env', '@babel/preset-react']
                    }
                }
            },
            {
                test: /.css$/,
                use: [
                    MiniCssExtractPlugin.loader,
                    "css-loader", "postcss-loader"
                ]
            },
            { // We could use '@babel/preset-typescript here', but 'ts-loader' performs type checks
                test: /\.tsx?$/,
                use: 'ts-loader',
                exclude: /node_modules/
            }
        ]
    },

    resolve: {
        extensions: ['.tsx', '.ts', '.js']
    }
}