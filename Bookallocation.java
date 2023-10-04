import java.util.Arrays;

public class BookAllocation {
    
    // Function to check if it's possible to allocate pages such that
    // the maximum number of pages allocated to any student is at most mid
    static boolean isPossible(int[] books, int n, int students, int mid) {
        int studentsRequired = 1;
        int pagesAllocated = 0;
        
        for (int i = 0; i < n; i++) {
            if (books[i] > mid) {
                return false; // If a single book has more pages than mid, it can't be allocated.
            }
            
            if (pagesAllocated + books[i] > mid) {
                studentsRequired++;
                pagesAllocated = books[i];
                if (studentsRequired > students) {
                    return false; // We need more students than available.
                }
            } else {
                pagesAllocated += books[i];
            }
        }
        
        return true;
    }
    
    // Function to find the minimum number of pages such that each student
    // gets at least one book and the maximum number of pages allocated to any
    // student is minimized.
    static int findMinPages(int[] books, int students) {
        int n = books.length;
        if (n < students) {
            return -1; // Not enough books to allocate to each student.
        }
        
        int totalPages = 0;
        int maxPage = Integer.MIN_VALUE;
        
        for (int i = 0; i < n; i++) {
            totalPages += books[i];
            maxPage = Math.max(maxPage, books[i]);
        }
        
        int left = maxPage;
        int right = totalPages;
        int result = -1;
        
        while (left <= right) {
            int mid = (left + right) / 2;
            if (isPossible(books, n, students, mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        int[] books = {12, 34, 67, 90};
        int students = 2;
        
        int minPages = findMinPages(books, students);
        
        if (minPages != -1) {
            System.out.println("The minimum number of pages each student can get is: " + minPages);
        } else {
            System.out.println("It is not possible to allocate books to students with the given constraints.");
        }
    }
}
