//import FileReader;

var filename;

process.argv.forEach(function (val, index, array) {
    if (index > 1) {
        filename = val;
    }
});

readText = function(file) {
    var reader = new FileReader();

    reader.readAsText(file);
    return reader.result;
}

console.log(readText(filename));