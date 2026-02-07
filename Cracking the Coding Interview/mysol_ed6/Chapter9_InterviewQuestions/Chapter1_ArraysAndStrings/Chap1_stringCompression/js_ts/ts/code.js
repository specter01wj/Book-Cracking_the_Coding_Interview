// =========================================
// Solution 1 (BAD)
// =========================================
function compressBad(str) {
    let compressed = "";
    let count = 0;
    for (let i = 0; i < str.length; i++) {
        count++;
        if (i + 1 >= str.length || str[i] !== str[i + 1]) {
            compressed += str[i] + count;
            count = 0;
        }
    }
    return compressed.length < str.length ? compressed : str;
}
// =========================================
// Solution 2
// =========================================
function compress(str) {
    let result = [];
    let count = 0;
    for (let i = 0; i < str.length; i++) {
        count++;
        if (i + 1 >= str.length || str[i] !== str[i + 1]) {
            result.push(str[i] + count);
            count = 0;
        }
    }
    const compressed = result.join('');
    return compressed.length < str.length ? compressed : str;
}
// =========================================
// Solution 3
// =========================================
function compressOptimized(str) {
    let finalLength = 0;
    let count = 0;
    for (let i = 0; i < str.length; i++) {
        count++;
        if (i + 1 >= str.length || str[i] !== str[i + 1]) {
            finalLength += 1 + count.toString().length;
            count = 0;
        }
    }
    if (finalLength >= str.length)
        return str;
    return compress(str);
}
// Tests
const tests = ["aabcccccaaa", "abcdef"];
let output = ">>> CTCI Chapter 1.6 – String Compression <<<br><br>";
output += "<b>Solution 1 (Bad)</b><br>";
tests.forEach(s => output += `${s} → ${compressBad(s)}<br>`);
output += "<br><b>Solution 2</b><br>";
tests.forEach(s => output += `${s} → ${compress(s)}<br>`);
output += "<br><b>Solution 3</b><br>";
tests.forEach(s => output += `${s} → ${compressOptimized(s)}<br>`);
document.querySelector('#t1').innerHTML = output;
