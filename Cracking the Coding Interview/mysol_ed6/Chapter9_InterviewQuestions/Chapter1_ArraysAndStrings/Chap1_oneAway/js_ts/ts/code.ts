function getCharNumber(c: string): number {
    const code = c.toLowerCase().charCodeAt(0);
    if (code >= 97 && code <= 122) return code - 97;
    return -1;
}

// Solution 1
function isPermutationOfPalindrome(str: string): boolean {
    const table = new Array<number>(26).fill(0);
    for (const c of str) {
        const x = getCharNumber(c);
        if (x !== -1) table[x]++;
    }
    let foundOdd = false;
    for (const count of table) {
        if (count % 2 === 1) {
            if (foundOdd) return false;
            foundOdd = true;
        }
    }
    return true;
}

// Solution 2
function isPermutationOfPalindromeOptimized(str: string): boolean {
    const table = new Array<number>(26).fill(0);
    let countOdd = 0;
    for (const c of str) {
        const x = getCharNumber(c);
        if (x !== -1) {
            table[x]++;
            countOdd += (table[x] % 2 === 1) ? 1 : -1;
        }
    }
    return countOdd <= 1;
}

// Solution 3
function isPermutationOfPalindromeBitVector(str: string): boolean {
    let bitVector = 0;
    for (const c of str) {
        const x = getCharNumber(c);
        if (x !== -1) {
            bitVector ^= (1 << x);
        }
    }
    return bitVector === 0 || (bitVector & (bitVector - 1)) === 0;
}

// Tests
const tests = ["Tact Coa", "racecar", "hello"];

let output = ">>> CTCI Chapter 1.4 – Palindrome Permutation <<<br><br>";
tests.forEach(s => {
    output += `"${s}" → `
        + isPermutationOfPalindrome(s) + " | "
        + isPermutationOfPalindromeOptimized(s) + " | "
        + isPermutationOfPalindromeBitVector(s) + "<br>";
});

(document.querySelector('#t1') as HTMLElement).innerHTML = output;