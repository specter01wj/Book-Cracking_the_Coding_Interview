// =========================================
// Solution 1: Sort and compare
// =========================================
function permutationBySort(s: string, t: string): boolean {
    if (s.length !== t.length) return false;
    return s.split('').sort().join('') ===
           t.split('').sort().join('');
}

// =========================================
// Solution 2: Character count (ASCII)
// =========================================
function permutationByCount(s: string, t: string): boolean {
    if (s.length !== t.length) return false;

    const letters: number[] = new Array(128).fill(0);

    for (const c of s) {
        letters[c.charCodeAt(0)]++;
    }

    for (const c of t) {
        letters[c.charCodeAt(0)]--;
        if (letters[c.charCodeAt(0)] < 0) {
            return false;
        }
    }
    return true;
}

// =========================================
// Tests + DOM output
// =========================================
const tests: [string, string][] = [
    ["abc", "bca"],
    ["abc", "abcd"],
    ["God", "dog"],
    ["a b", "b a"]
];

let output = ">>> CTCI Chapter 1.2 – Check Permutation <<<br><br>";

output += "<b>Solution 1: permutationBySort</b><br>";
tests.forEach(t => {
    output += `"${t[0]}" vs "${t[1]}" → ${permutationBySort(t[0], t[1])}<br>`;
});

output += "<br><b>Solution 2: permutationByCount</b><br>";
tests.forEach(t => {
    output += `"${t[0]}" vs "${t[1]}" → ${permutationByCount(t[0], t[1])}<br>`;
});

const webHeading = document.querySelector('#t1') as HTMLElement;
webHeading.innerHTML = output;