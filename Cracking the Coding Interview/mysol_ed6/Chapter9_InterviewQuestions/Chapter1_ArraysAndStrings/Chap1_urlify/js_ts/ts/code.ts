// =========================================
// URLify (TypeScript version)
// =========================================
function urlify(str: string, trueLength: number): string {
    let result: string[] = [];

    for (let i = 0; i < trueLength; i++) {
        if (str[i] === ' ') {
            result.push('%', '2', '0');
        } else {
            result.push(str[i]);
        }
    }
    return result.join('');
}

// =========================================
// Tests + DOM output
// =========================================
const tests: [string, number][] = [
    ["Mr John Smith    ", 13],
    ["Hello World  ", 11],
    ["NoSpace", 7],
    [" a b   ", 4]
];

let output = ">>> CTCI Chapter 1.3 – URLify <<<br><br>";

tests.forEach(t => {
    output += `"${t[0]}" (len=${t[1]}) → ${urlify(t[0], t[1])}<br>`;
});

const webHeading = document.querySelector('#t1') as HTMLElement;
webHeading.innerHTML = output;