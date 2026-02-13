function isSubstring(big, small) {
    if (big.indexOf(small) >= 0) {
        return true;
    }
    else {
        return false;
    }
}
function isRotation(s1, s2) {
    if (s1.length !== s2.length) {
        return false;
    }
    const combined = s1 + s1;
    return isSubstring(combined, s2);
}
// Tests
const tests = [
    ["waterbottle", "erbottlewat"],
    ["hello", "llohe"],
    ["hello", "lloeh"],
    ["abc", "cab"],
    ["abc", "acb"]
];
let output = ">>> CTCI Chapter 1.9 – String Rotation <<<br><br>";
output += "<b>Solution</b><br>";
tests.forEach(pair => {
    const s1 = pair[0];
    const s2 = pair[1];
    output += `${s1} , ${s2} → ${isRotation(s1, s2)}<br>`;
});
document.querySelector('#t1').innerHTML = output;
