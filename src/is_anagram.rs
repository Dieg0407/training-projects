use std::collections::HashMap;

// leet code url: https://leetcode.com/problems/valid-anagram/
#[allow(dead_code)]
struct Solution;

#[allow(dead_code)]
impl Solution {
    pub fn is_anagram(s: String, t: String) -> bool {
        if s.len() != t.len() {
            return false;
        }
        let mut map: HashMap<char, i8> = HashMap::new();
        for c in s.chars() {
            if let Some(e) = map.get_mut(&c) {
                *e = *e + 1;
            } else {
                map.insert(c, 1);
            }
        }
        for c in t.chars() {
            if let Some(e) = map.get_mut(&c) {
                *e = *e - 1;
                if *e == 0 {
                    map.remove(&c);
                }
            } else {
                return false;
            }
        }

        map.len() == 0
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn example01() {
        let s = "anagram".to_string();
        let t = "nagaram".to_string();

        assert_eq!(Solution::is_anagram(s, t), true);
    }

    #[test]
    fn example02() {
        let s = "rat".to_string();
        let t = "car".to_string();

        assert_eq!(Solution::is_anagram(s, t), false);
    }

    #[test]
    fn test_case_42() {
        let s = "\"hhbywxfzydbppjxnbhezsxepfexkzofxyqdvcgdvgnjbvihgufvgtuxtpioxgjuwawkbaerbsirrktcjcesepcocaglbassivfbjhikynfsvlbtkawstrclbkpuldshfgfvwjawownulsggsxhhqglbhjlgltfrqyjntgldlgorgatnjrsywlyngxrcwfojkmydmjbzuquqnzbpwlcbdzgdicctpkgtbdjgywkyrkityvohjbuvmzdfyicyplmrpygdhaeqbuxnehfybsysnnmzbhprsyjmtpcrzeqqbirofzjtlsyofdyeffkvpuhzaflwjfhnsuyryetjuajjnjwvlvrhvpenaarnzoafztixjrisfzdlepcwhxudjpsiirtofymnovacjmpdjtethjqfwduekczlqhsfjgqesyoxcfooagrdhyvsmssbhsclnlblhobvhwtpyftolneozlhbtjagpgqnnapktyevdvjfwdnbwsbelweoflhyhifprieuvfcdkavqxkygjlaegqfmzndgxbsccjgpclxmlpstrqjtqyvlqcopnahqvnpvkjimfdlosvletmamqjvotqwhadutmfvlgldniixvdkmymfadckuaglgbuttymoqmzkaeqxugsrnfyxzcamwxujgzupefretsvbdweuwwcizjvhcowtmwgkdafcpzctpsjwdocgofivyshwdinbdhbxdfhjsrrsfchxkeqndgzauyprwfnrbmunanqnhmjhrufoinakwaciaoerioqffmipfqujfxwofqdyjbhagkyvmnxcwomgnmwlaodxgkgthnuctoozxrebjiynjwohtgukyneyofabpfdrxklopmxxiwjuxqpaazknscagfiaetmmwnwpzceglupqvlctywtpluoqbzdultcsudubqclbwlxyfboimfwriugfulbntvdwnxgiycxvennakpodorvpiknkridcumsovvfziki...".to_string();
        let t = "\"rmhdxtymfgxjluxmeeerhxrrtjgecmyfdhevakyosvvuwgbwmxiakbmtqjjpgctjojdqatuelqmfeldgoprxbzcylajhhjngqlmporofjpoyhrkptrbpzvvnkrqjovfzmmqybqxzjxarhguqcyvlugwmnzwtffanzuajraqbvjflgalgcwuhmrkblobybwouffzptbvkmwoklczfhvkqsirkqvhkryyxpkyfnwnjudvllmznupldkpoplwdlfzncwxdlgvgkehpcudqybnyhofpjnjmahnsrntxjtgocwsrnukejehwzxdetglgahdvzyypdztqwbnfrnytxprhtqjgobzjyewuasgnryjoyvwhdmemppixqcowgmicbmsukgodwgnsdieuqyzuysedmsphmritlqtnvnvfnmgnghxdsmmnzdhdalhfsfsppupteyrlajxrlbyqteaytgpsckottqnibbxhmkwuqfftwvibzsnaumsbohxeoaulsmwuvjcimscyqhtioekembdwfozglussscbzejqnvmcpfwdmxshhrkquacplctruyklkxcfovcbqllmyghdbkhdmawpmalrsgymgmbleftwtvldxkqprcbcbneyyluoyydtcvkimqjoinwntfxfbwqjbufqansdlgrgauzfwxpzrjxpesmozcsczbfzfeziedqzwywtxhyfnpbxfgbqcsfwdiplegcsrseqsagvkpjrupkaxffrddebpntocmkbarvdrhvujedwnktsdimzcbqlqyizozxcexqpoefjpznegsrtbfanqdyqaaudcklplvxojfuzccmtauaxurbwbexulqbxooadcrlxbtggxcycadratsuqavwwkdqtutptdpbnxeicmackhyvjhkfbayjmymjwejybhoryyqjjmwqzhpgulovxaccychffcplvkqfqghmgmdsigkugsevwkvzgpxiolhtzocdiphokfitgg...".to_string();

        assert_eq!(Solution::is_anagram(s, t), true);
    }
}
