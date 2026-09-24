class LinkedListNode {

    constructor(data) {
        this.data = data;
        this.next = null;
    }

}

class Result {

    constructor(node, result) {
        this.node = node;
        this.result = result;
    }

}

class Chap2_palindrome {

    //====================================================
    // Helpers
    //====================================================

    buildList(...values) {

        if (values.length === 0) {
            return null;
        }

        const head = new LinkedListNode(values[0]);
        let current = head;

        for (let i = 1; i < values.length; i++) {
            current.next = new LinkedListNode(values[i]);
            current = current.next;
        }

        return head;
    }

    listToString(head) {

        if (head === null) {
            return "Empty";
        }

        const result = [];

        while (head !== null) {
            result.push(head.data);
            head = head.next;
        }

        return result.join(" -> ");
    }

    //====================================================
    // Solution 1 (Book)
    // Reverse and Compare
    //====================================================

    isPalindrome(head) {

        const reversed = this.reverseAndClone(head);

        return this.isEqual(head, reversed);
    }

    reverseAndClone(node) {

        let head = null;

        while (node !== null) {

            const n = new LinkedListNode(node.data);

            n.next = head;
            head = n;

            node = node.next;
        }

        return head;
    }

    isEqual(one, two) {

        while (one !== null && two !== null) {

            if (one.data !== two.data) {
                return false;
            }

            one = one.next;
            two = two.next;
        }

        return one === null && two === null;
    }

    //====================================================
    // Solution 2 (Book)
    // Iterative Using Stack
    //====================================================

    isPalindrome2(head) {

        let fast = head;
        let slow = head;

        const stack = [];

        while (fast !== null && fast.next !== null) {

            stack.push(slow.data);

            slow = slow.next;
            fast = fast.next.next;
        }

        // Odd number of nodes, skip the middle node.
        if (fast !== null) {
            slow = slow.next;
        }

        while (slow !== null) {

            const top = stack.pop();

            if (top !== slow.data) {
                return false;
            }

            slow = slow.next;
        }

        return true;
    }

    //====================================================
    // Solution 3 (Book)
    // Recursive
    //====================================================

    isPalindrome3(head) {

        const length = this.lengthOfList(head);

        const p = this.isPalindromeRecurse(head, length);

        return p.result;
    }

    isPalindromeRecurse(head, length) {

        if (head === null || length <= 0) {

            // Even number of nodes.
            return new Result(head, true);

        } else if (length === 1) {

            // Odd number of nodes.
            return new Result(head.next, true);
        }

        const res = this.isPalindromeRecurse(head.next, length - 2);

        if (!res.result || res.node === null) {
            return res;
        }

        res.result = (head.data === res.node.data);

        res.node = res.node.next;

        return res;
    }

    lengthOfList(head) {

        let size = 0;

        while (head !== null) {
            size++;
            head = head.next;
        }

        return size;
    }

}

const test = new Chap2_palindrome();

const tests = [

    test.buildList(),

    test.buildList(1),

    test.buildList(1, 1),

    test.buildList(1, 2),

    test.buildList(1, 2, 1),

    test.buildList(1, 2, 2, 1),

    test.buildList(1, 2, 3, 2, 1),

    test.buildList(1, 2, 3, 4, 1),

    test.buildList(0, 1, 2, 1, 0),

    test.buildList(5, 4, 4, 5),

    test.buildList(9, 8, 7, 8, 9),

    test.buildList(1, 2, 3, 4, 5)

];

let output = ">>> CTCI Chapter 2.6 - Palindrome <<<br><br>";

//====================================================
// Solution 1
//====================================================

output += "<b>========== Solution 1 : Reverse and Compare ==========</b><br><br>";

tests.forEach(head => {

    output += `List : ${test.listToString(head)}<br>`;
    output += `Result : ${test.isPalindrome(head)}<br><br>`;

});

//====================================================
// Solution 2
//====================================================

output += "<b>========== Solution 2 : Stack ==========</b><br><br>";

tests.forEach(head => {

    output += `List : ${test.listToString(head)}<br>`;
    output += `Result : ${test.isPalindrome2(head)}<br><br>`;

});

//====================================================
// Solution 3
//====================================================

output += "<b>========== Solution 3 : Recursive ==========</b><br><br>";

tests.forEach(head => {

    output += `List : ${test.listToString(head)}<br>`;
    output += `Result : ${test.isPalindrome3(head)}<br><br>`;

});

output += "<b>Study Complete.</b>";

document.querySelector("#t1").innerHTML = output;