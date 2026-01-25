// =========================================
// Solution 1: isUniqueChars (ASCII boolean array)
// Time: O(n), Space: O(1)
// =========================================
function isUniqueChars(str) {
    if (str.length > 128)
        return false;
    const seen = new Array(128).fill(false);
    for (let i = 0; i < str.length; i++) {
        const code = str.charCodeAt(i);
        if (seen[code]) {
            return false;
        }
        seen[code] = true;
    }
    return true;
}
// =========================================
// Solution 2: isUniqueNoDS (no data structures)
// Time: O(n^2), Space: O(1)
// =========================================
function isUniqueNoDS(str) {
    for (let i = 0; i < str.length; i++) {
        for (let j = i + 1; j < str.length; j++) {
            if (str[i] === str[j]) {
                return false;
            }
        }
    }
    return true;
}
// =========================================
// Solution 3: isUniqueBitVector (a–z only)
// Time: O(n), Space: O(1)
// =========================================
function isUniqueBitVector(str) {
    let checker = 0;
    for (let i = 0; i < str.length; i++) {
        const val = str.charCodeAt(i) - 97; // 'a'
        if ((checker & (1 << val)) !== 0) {
            return false;
        }
        checker |= (1 << val);
    }
    return true;
}
// =========================================
// Solution 4: isUniqueHashSet (general-purpose)
// Time: O(n), Space: O(n)
// =========================================
function isUniqueHashSet(str) {
    const set = new Set();
    for (const ch of str) {
        if (set.has(ch))
            return false;
        set.add(ch);
    }
    return true;
}
// =========================================
// Tests + DOM output
// =========================================
const inputs = ["abcdef", "hello", "", "AaBbCc"];
let output = ">>> CTCI Chapter 1.1 – Is Unique <<<<br><br>";
output += "<b>Solution 1: isUniqueChars (ASCII)</b><br>";
inputs.forEach(s => {
    output += `Input: "${s}" → ${isUniqueChars(s)}<br>`;
});
output += "<br><b>Solution 2: isUniqueNoDS</b><br>";
inputs.forEach(s => {
    output += `Input: "${s}" → ${isUniqueNoDS(s)}<br>`;
});
output += "<br><b>Solution 3: isUniqueBitVector (a–z only)</b><br>";
["abcdef", "hello", "xyz"].forEach(s => {
    output += `Input: "${s}" → ${isUniqueBitVector(s)}<br>`;
});
output += "<br><b>Solution 4: isUniqueHashSet</b><br>";
inputs.forEach(s => {
    output += `Input: "${s}" → ${isUniqueHashSet(s)}<br>`;
});
const webHeading = document.querySelector('#t1');
webHeading.innerHTML = output;
