/*
 Navicat Premium Data Transfer

 Source Server         : Rion
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : rion_oj

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 06/10/2023 21:09:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '内容',
  `tags` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标签列表（json 数组）',
  `answer` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '题目答案',
  `submitNum` int(0) NOT NULL DEFAULT 0 COMMENT '题目提交数',
  `acceptedNum` int(0) NOT NULL DEFAULT 0 COMMENT '题目通过数',
  `judgeCase` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '判题用例（json 数组）',
  `judgeConfig` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '判题配置（json 对象）',
  `thumbNum` int(0) NOT NULL DEFAULT 0 COMMENT '点赞数',
  `favourNum` int(0) NOT NULL DEFAULT 0 COMMENT '收藏数',
  `userId` bigint(0) NOT NULL COMMENT '创建用户 id',
  `createTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `isDelete` tinyint(0) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_userId`(`userId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1709491123900231683 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '题目' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES (1699610963331805186, '两数之和', '给定一个整数数组 `nums` 和一个整数目标值 `target`，请你在该数组中找出 **和为目标值** *`target`* 的那 **两个** 整数，并返回它们的数组下标。\n\n你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。\n\n你可以按任意顺序返回答案。\n\n\n\n**示例 1：**\n\n```\n输入：nums = [2,7,11,15], target = 9\n输出：[0,1]\n解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。\n```\n\n**示例 2：**\n\n```\n输入：nums = [3,2,4], target = 6\n输出：[1,2]\n```\n\n**示例 3：**\n\n```\n输入：nums = [3,3], target = 6\n输出：[0,1]\n```\n\n \n\n**提示：**\n\n- `2 <= nums.length <= 104`\n- `-109 <= nums[i] <= 109`\n- `-109 <= target <= 109`\n- **只会存在一个有效答案**', '[\"数组\",\"哈希表\"]', 'test', 0, 0, '[{\"input\":\"1 2\",\"output\":\"3\"}]', '{\"timeLimit\":1000,\"memoryLimit\":500,\"stackLimit\":50}', 0, 0, 1699083758717698049, '2023-09-07 10:30:05', '2023-10-04 18:01:13', 0);
INSERT INTO `question` VALUES (1699611474453884929, '123', '123', '[\"123\"]', '123', 0, 0, '[{\"input\":\"zczc\",\"output\":\"zcxz\"}]', '{\"timeLimit\":120,\"memoryLimit\":120,\"stackLimit\":120}', 0, 0, 1699083758717698049, '2023-09-07 10:32:07', '2023-09-08 15:32:02', 1);
INSERT INTO `question` VALUES (1699611479096979458, 'test', 'test', '[\"栈\",\"堆\"]', 'test', 0, 0, NULL, NULL, 0, 0, 1699083758717698049, '2023-09-07 10:32:08', '2023-09-08 17:18:20', 1);
INSERT INTO `question` VALUES (1699611483014459393, 'test', 'test', '[\"栈\",\"堆\"]', 'test', 0, 0, NULL, NULL, 0, 0, 1699083758717698049, '2023-09-07 10:32:09', '2023-09-08 17:19:36', 1);
INSERT INTO `question` VALUES (1699747091984748546, '无重复字符的最长子串\n', '给定一个字符串 `s` ，请你找出其中不含有重复字符的 **最长子串** 的长度。\n\n \n\n**示例 1:**\n\n```\n输入: s = \"abcabcbb\"\n输出: 3 \n解释: 因为无重复字符的最长子串是 \"abc\"，所以其长度为 3。\n```\n\n**示例 2:**\n\n```\n输入: s = \"bbbbb\"\n输出: 1\n解释: 因为无重复字符的最长子串是 \"b\"，所以其长度为 1。\n```\n\n**示例 3:**\n\n```\n输入: s = \"pwwkew\"\n输出: 3\n解释: 因为无重复字符的最长子串是 \"wke\"，所以其长度为 3。\n     请注意，你的答案必须是 子串 的长度，\"pwke\" 是一个子序列，不是子串。\n```\n\n \n\n**提示：**\n\n- `0 <= s.length <= 5 * 104`\n- `s` 由英文字母、数字、符号和空格组成', '[\"哈希表\",\"字符串\",\"滑动窗口\"]', 'test', 0, 0, '[{\"input\":\"1 0\",\"output\":\"4 2\"}]', '{\"timeLimit\":5000,\"memoryLimit\":1000,\"stackLimit\":1000}', 0, 0, 1699083758717698049, '2023-09-07 19:31:01', '2023-09-25 19:08:09', 0);
INSERT INTO `question` VALUES (1699747691057188865, 'test', 'test', '[\"栈\",\"堆\"]', 'test', 0, 0, '[{\"input\":\"1 0\",\"output\":\"4 2\"}]', '{\"timeLimit\":5000,\"memoryLimit\":1000,\"stackLimit\":1000}', 0, 0, 1699083758717698049, '2023-09-07 19:33:23', '2023-09-07 19:33:23', 0);
INSERT INTO `question` VALUES (1699749256283361282, 'test', 'test', '[\"栈\",\"堆\"]', 'test', 0, 0, '[{\"input\":\"1 0\",\"output\":\"4 2\"}]', '{\"timeLimit\":5000,\"memoryLimit\":1000,\"stackLimit\":1000}', 0, 0, 1699083758717698049, '2023-09-07 19:39:37', '2023-09-07 19:39:37', 0);
INSERT INTO `question` VALUES (1699749425422864385, '两数相加', '给你两个 **非空** 的链表，表示两个非负的整数。它们每位数字都是按照 **逆序** 的方式存储的，并且每个节点只能存储 **一位** 数字。\n\n请你将两个数相加，并以相同形式返回一个表示和的链表。\n\n你可以假设除了数字 0 之外，这两个数都不会以 0 开头。\n\n \n\n**示例 1：**\n\n![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2021/01/02/addtwonumber1.jpg)\n\n```\n输入：l1 = [2,4,3], l2 = [5,6,4]\n输出：[7,0,8]\n解释：342 + 465 = 807.\n```\n\n**示例 2：**\n\n```\n输入：l1 = [0], l2 = [0]\n输出：[0]\n```\n\n**示例 3：**\n\n```\n输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]\n输出：[8,9,9,9,0,0,0,1]\n```\n\n \n\n**提示：**\n\n- 每个链表中的节点数在范围 `[1, 100]` 内\n- `0 <= Node.val <= 9`\n- 题目数据保证列表表示的数字不含前导零', '[\"递归\",\"链表\",\"数学\"]', 'test', 0, 0, '[{\"input\":\"1 0\",\"output\":\"4 2\"}]', '{\"timeLimit\":5000,\"memoryLimit\":1000,\"stackLimit\":1000}', 0, 0, 1699083758717698049, '2023-09-07 19:40:17', '2023-09-25 19:08:35', 0);
INSERT INTO `question` VALUES (1700114672298012673, '寻找两个正序数组的中位数', '给定两个大小分别为 `m` 和 `n` 的正序（从小到大）数组 `nums1` 和 `nums2`。请你找出并返回这两个正序数组的 **中位数** 。\n\n算法的时间复杂度应该为 `O(log (m+n))` 。\n\n \n\n**示例 1：**\n\n```\n输入：nums1 = [1,3], nums2 = [2]\n输出：2.00000\n解释：合并数组 = [1,2,3] ，中位数 2\n```\n\n**示例 2：**\n\n```\n输入：nums1 = [1,2], nums2 = [3,4]\n输出：2.50000\n解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5\n```\n\n \n\n \n\n**提示：**\n\n- `nums1.length == m`\n- `nums2.length == n`\n- `0 <= m <= 1000`\n- `0 <= n <= 1000`\n- `1 <= m + n <= 2000`\n- `-106 <= nums1[i], nums2[i] <= 106`', '[\"数组\",\"二分查找\",\"分治\"]', 'testzc', 0, 0, '[{\"input\":\"1 0\",\"output\":\"4 2\"}]', '{\"timeLimit\":4997,\"memoryLimit\":1007,\"stackLimit\":1000}', 0, 0, 1699083758717698049, '2023-09-08 19:51:39', '2023-09-25 19:09:25', 0);
INSERT INTO `question` VALUES (1700116154426634242, 'qwe', 'asdas', '[\"qqe\",\"asd\",\"q\"]', 'asd', 0, 0, '[{\"input\":\"zxc\",\"output\":\"asd\"}]', '{\"timeLimit\":1000,\"memoryLimit\":500,\"stackLimit\":50}', 0, 0, 1699083758717698049, '2023-09-08 19:57:32', '2023-09-08 19:57:32', 0);
INSERT INTO `question` VALUES (1709491123900231682, '测试题目', '### 测试题目\na+b', '[\"简单\"]', 'aaa', 0, 0, '[{\"input\":\"1 2\",\"output\":\"2 3\"}]', '{\"timeLimit\":1000,\"memoryLimit\":1000,\"stackLimit\":1000}', 0, 0, 1699083758717698049, '2023-10-04 16:50:19', '2023-10-04 16:50:19', 0);

-- ----------------------------
-- Table structure for question_submit
-- ----------------------------
DROP TABLE IF EXISTS `question_submit`;
CREATE TABLE `question_submit`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `language` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编程语言',
  `code` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户代码',
  `judgeInfo` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '判题信息（json 对象）',
  `status` int(0) NOT NULL DEFAULT 0 COMMENT '判题状态（0 - 待判题、1 - 判题中、2 - 成功、3 - 失败）',
  `questionId` bigint(0) NOT NULL COMMENT '题目 id',
  `userId` bigint(0) NOT NULL COMMENT '创建用户 id',
  `createTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `isDelete` tinyint(0) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_questionId`(`questionId`) USING BTREE,
  INDEX `idx_userId`(`userId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1710234720555524098 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '题目提交' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question_submit
-- ----------------------------
INSERT INTO `question_submit` VALUES (1699656918299119618, 'java', '1qeq2', '{}', 0, 1699611474453884929, 1699083758717698049, '2023-09-07 13:32:42', '2023-09-07 13:32:42', 0);
INSERT INTO `question_submit` VALUES (1699656927514005505, 'java', '1qe31q2', '{}', 0, 1699611474453884929, 1699083758717698049, '2023-09-07 13:32:44', '2023-09-07 13:32:44', 0);
INSERT INTO `question_submit` VALUES (1699656937014104065, 'java', '1qe3141q2', '{}', 0, 1699611474453884929, 1699083758717698049, '2023-09-07 13:32:46', '2023-09-07 13:32:46', 0);
INSERT INTO `question_submit` VALUES (1706489680859578370, 'java', 'sdada', '{}', 0, 1699610963331805186, 1699083758717698049, '2023-09-26 10:03:39', '2023-09-26 10:03:39', 0);
INSERT INTO `question_submit` VALUES (1706490725681676289, 'java', 'gf', '{}', 0, 1699610963331805186, 1699083758717698049, '2023-09-26 10:07:48', '2023-09-26 10:07:48', 0);
INSERT INTO `question_submit` VALUES (1706491158689038338, 'java', '@PostMapping(\"/add\")\r\n    public BaseResponse<Long> addQuestion(@RequestBody QuestionAddRequest questionAddRequest, HttpServletRequest request) {\r\n        if (questionAddRequest == null) {\r\n            throw new BusinessException(ErrorCode.PARAMS_ERROR);\r\n        }\r\n        Question question = new Question();\r\n        BeanUtils.copyProperties(questionAddRequest, question);\r\n        List<String> tags = questionAddRequest.getTags();\r\n        if (tags != null) {\r\n            question.setTags(GSON.toJson(tags));\r\n        }\r\n        List<JudgeCase> judgeCase = questionAddRequest.getJudgeCase();\r\n        if (judgeCase != null) {\r\n            question.setJudgeCase(GSON.toJson(judgeCase));\r\n        }\r\n        JudgeConfig judgeConfig = questionAddRequest.getJudgeConfig();\r\n        if (judgeConfig != null) {\r\n            question.setJudgeConfig(GSON.toJson(judgeConfig));\r\n        }\r\n        questionService.validQuestion(question, true);\r\n        User loginUser = userService.getLoginUser(request);\r\n        question.setUserId(loginUser.getId());\r\n        question.setFavourNum(0);\r\n        question.setThumbNum(0);\r\n        boolean result = questionService.save(question);\r\n        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);\r\n        long newQuestionId = question.getId();\r\n        return ResultUtils.success(newQuestionId);\r\n    }', '{}', 0, 1699610963331805186, 1699083758717698049, '2023-09-26 10:09:31', '2023-09-26 10:09:31', 0);
INSERT INTO `question_submit` VALUES (1706492395434098689, 'java', 'test', '{}', 0, 1699747091984748546, 1699083758717698049, '2023-09-26 10:14:26', '2023-09-26 10:14:26', 0);
INSERT INTO `question_submit` VALUES (1706492404477018113, 'java', 'test', '{}', 0, 1699747091984748546, 1699083758717698049, '2023-09-26 10:14:28', '2023-09-26 10:14:28', 0);
INSERT INTO `question_submit` VALUES (1706492405869527041, 'java', 'test', '{}', 0, 1699747091984748546, 1699083758717698049, '2023-09-26 10:14:29', '2023-09-26 10:14:29', 0);
INSERT INTO `question_submit` VALUES (1706492406720970753, 'java', 'test', '{}', 0, 1699747091984748546, 1699083758717698049, '2023-09-26 10:14:29', '2023-09-26 10:14:29', 0);
INSERT INTO `question_submit` VALUES (1706601546608906241, 'java', 'asd', '{\"message\":\"内存溢出\",\"memory\":1000,\"time\":1000}', 2, 1699610963331805186, 1699083758717698049, '2023-09-26 17:28:10', '2023-09-26 17:28:10', 0);
INSERT INTO `question_submit` VALUES (1706602085119791105, 'java', 'dzxdz', '{\"message\":\"Wrong Answer\",\"memory\":1000,\"time\":1000}', 2, 1700114672298012673, 1699083758717698049, '2023-09-26 17:30:18', '2023-09-26 17:30:18', 0);
INSERT INTO `question_submit` VALUES (1706602183744655362, 'java', 'testzc', '{\"message\":\"Wrong Answer\",\"memory\":1000,\"time\":1000}', 2, 1700114672298012673, 1699083758717698049, '2023-09-26 17:30:42', '2023-09-26 17:30:42', 0);
INSERT INTO `question_submit` VALUES (1708771272294461441, 'java', 'public class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println(\"结果:\" + (a + b));\r\n    }\r\n}', '{\"message\":\"Wrong Answer\",\"memory\":0,\"time\":58}', 2, 1700114672298012673, 1699083758717698049, '2023-10-02 17:09:53', '2023-10-02 17:09:54', 0);
INSERT INTO `question_submit` VALUES (1709447961995390977, 'java', 'int main', '{}', 0, 1699610963331805186, 1699083758717698049, '2023-10-04 13:58:48', '2023-10-04 13:58:48', 0);
INSERT INTO `question_submit` VALUES (1709447990906728449, 'java', 'int main', '{}', 0, 1699610963331805186, 1699083758717698049, '2023-10-04 13:58:55', '2023-10-04 13:58:55', 0);
INSERT INTO `question_submit` VALUES (1709491289277444098, 'java', 'aaa', '{}', 0, 1709491123900231682, 1699083758717698049, '2023-10-04 16:50:58', '2023-10-04 16:50:58', 0);
INSERT INTO `question_submit` VALUES (1709491553183051778, 'java', 'aaa', '{}', 0, 1699610963331805186, 1699083758717698049, '2023-10-04 16:52:01', '2023-10-04 16:52:01', 0);
INSERT INTO `question_submit` VALUES (1709494715822477313, 'java', 'public class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println(\"结果:\" + (a + b));\r\n    }\r\n}', '{}', 0, 1699610963331805186, 1699083758717698049, '2023-10-04 17:04:35', '2023-10-04 17:04:35', 0);
INSERT INTO `question_submit` VALUES (1709495305805860866, 'java', 'public class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println(\"结果:\" + (a + b));\r\n    }\r\n}', '{}', 0, 1699610963331805186, 1699083758717698049, '2023-10-04 17:06:56', '2023-10-04 17:06:56', 0);
INSERT INTO `question_submit` VALUES (1709495608097738754, 'java', 'public class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println(\"结果:\" + (a + b));\r\n    }\r\n}', '{}', 0, 1699610963331805186, 1699083758717698049, '2023-10-04 17:08:08', '2023-10-04 17:08:08', 0);
INSERT INTO `question_submit` VALUES (1709496033169477633, 'java', 'public class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println(\"结果:\" + (a + b));\r\n    }\r\n}', '{}', 0, 1699610963331805186, 1699083758717698049, '2023-10-04 17:09:49', '2023-10-04 17:09:49', 0);
INSERT INTO `question_submit` VALUES (1709496290301284353, 'java', 'public class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println(\"结果:\" + (a + b));\r\n    }\r\n}', '{}', 0, 1699610963331805186, 1699083758717698049, '2023-10-04 17:10:51', '2023-10-04 17:10:51', 0);
INSERT INTO `question_submit` VALUES (1709497780201951233, 'java', 'public class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println(\"结果:\" + (a + b));\r\n    }\r\n}', '{}', 0, 1699610963331805186, 1699083758717698049, '2023-10-04 17:16:46', '2023-10-04 17:16:46', 0);
INSERT INTO `question_submit` VALUES (1709499847863791617, 'java', 'public class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println(\"结果:\" + (a + b));\r\n    }\r\n}', '{\"message\":\"Wrong Answer\",\"memory\":0,\"time\":0}', 2, 1699610963331805186, 1699083758717698049, '2023-10-04 17:24:59', '2023-10-04 17:25:13', 0);
INSERT INTO `question_submit` VALUES (1709500790785273857, 'java', 'public class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println(\"结果:\" + (a + b));\r\n    }\r\n}', '{\"message\":\"Wrong Answer\",\"memory\":0,\"time\":0}', 2, 1699610963331805186, 1699083758717698049, '2023-10-04 17:28:44', '2023-10-04 17:45:09', 0);
INSERT INTO `question_submit` VALUES (1709504820718694401, 'java', 'public class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println(\"结果:\" + (a + b));\r\n    }\r\n}', '{\"message\":\"Wrong Answer\",\"memory\":0,\"time\":0}', 2, 1699610963331805186, 1699083758717698049, '2023-10-04 17:44:44', '2023-10-04 17:47:46', 0);
INSERT INTO `question_submit` VALUES (1709505611265306625, 'java', 'public class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println(\"结果:\" + (a + b));\r\n    }\r\n}', '{\"message\":\"Wrong Answer\",\"memory\":0,\"time\":0}', 2, 1699610963331805186, 1699083758717698049, '2023-10-04 17:47:53', '2023-10-04 17:48:06', 0);
INSERT INTO `question_submit` VALUES (1709505989708967937, 'java', 'public class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println(\"结果:\" + (a + b));\r\n    }\r\n}', '{\"message\":\"Wrong Answer\",\"memory\":0,\"time\":0}', 2, 1699610963331805186, 1699083758717698049, '2023-10-04 17:49:23', '2023-10-04 17:49:39', 0);
INSERT INTO `question_submit` VALUES (1709506209599549441, 'java', 'public class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println(\"结果:\" + (a + b));\r\n    }\r\n}', '{\"message\":\"Wrong Answer\",\"memory\":0,\"time\":0}', 2, 1699610963331805186, 1699083758717698049, '2023-10-04 17:50:16', '2023-10-04 17:50:36', 0);
INSERT INTO `question_submit` VALUES (1709506475925270530, 'java', 'public class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println(\"结果:\" + (a + b));\r\n    }\r\n}', '{\"message\":\"Wrong Answer\",\"memory\":0,\"time\":0}', 2, 1699610963331805186, 1699083758717698049, '2023-10-04 17:51:19', '2023-10-04 17:53:02', 0);
INSERT INTO `question_submit` VALUES (1709506941291687938, 'java', 'public class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println(\"结果:\" + (a + b));\r\n    }\r\n}', '{\"message\":\"Wrong Answer\",\"memory\":0,\"time\":0}', 2, 1699610963331805186, 1699083758717698049, '2023-10-04 17:53:10', '2023-10-04 17:55:22', 0);
INSERT INTO `question_submit` VALUES (1709507462547206146, 'java', 'public class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println(\"结果:\" + (a + b));\r\n    }\r\n}', '{}', 1, 1699610963331805186, 1699083758717698049, '2023-10-04 17:55:14', '2023-10-04 17:55:34', 0);
INSERT INTO `question_submit` VALUES (1709507564116471810, 'java', 'public class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println(\"结果:\" + (a + b));\r\n    }\r\n}', '{}', 1, 1699610963331805186, 1699083758717698049, '2023-10-04 17:55:39', '2023-10-04 17:55:49', 0);
INSERT INTO `question_submit` VALUES (1709507833256570881, 'java', 'public class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println(\"结果:\" + (a + b));\r\n    }\r\n}', '{\"message\":\"Wrong Answer\",\"memory\":0,\"time\":0}', 2, 1699610963331805186, 1699083758717698049, '2023-10-04 17:56:43', '2023-10-04 17:58:44', 0);
INSERT INTO `question_submit` VALUES (1709508365794766850, 'java', 'public class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println(\"结果:\" + (a + b));\r\n    }\r\n}', '{\"message\":\"Wrong Answer\",\"memory\":0,\"time\":0}', 2, 1699610963331805186, 1699083758717698049, '2023-10-04 17:58:50', '2023-10-04 18:01:29', 0);
INSERT INTO `question_submit` VALUES (1709509064020553730, 'java', 'public class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println(\"结果:\" + (a + b));\r\n    }\r\n}', '{\"message\":\"Wrong Answer\",\"memory\":0,\"time\":53}', 2, 1699610963331805186, 1699083758717698049, '2023-10-04 18:01:36', '2023-10-04 18:05:21', 0);
INSERT INTO `question_submit` VALUES (1709510140673224705, 'java', 'public class Main {\r\n    public static void main(String[] args) {\r\n        int a = Integer.parseInt(args[0]);\r\n        int b = Integer.parseInt(args[1]);\r\n        System.out.println(a + b);\r\n    }\r\n}', '{\"message\":\"Accepted\",\"memory\":0,\"time\":56}', 2, 1699610963331805186, 1699083758717698049, '2023-10-04 18:05:53', '2023-10-04 18:06:41', 0);
INSERT INTO `question_submit` VALUES (1710232004408139778, 'java', 'da', '{}', 0, 1709491123900231682, 1699083758717698049, '2023-10-06 17:54:19', '2023-10-06 17:54:19', 0);
INSERT INTO `question_submit` VALUES (1710232326430023681, 'java', 'asdas', '{}', 0, 1699610963331805186, 1699083758717698049, '2023-10-06 17:55:35', '2023-10-06 17:55:35', 0);
INSERT INTO `question_submit` VALUES (1710233781257920514, 'java', 'aaa', '{}', 1, 1699610963331805186, 1699083758717698049, '2023-10-06 18:01:22', '2023-10-06 18:01:22', 0);
INSERT INTO `question_submit` VALUES (1710234720555524097, 'java', 'asdas', '{\"message\":\"Wrong Answer\",\"memory\":1000,\"time\":1000}', 2, 1699610963331805186, 1699083758717698049, '2023-10-06 18:05:06', '2023-10-06 18:05:07', 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `userAccount` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账号',
  `userPassword` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `mpOpenId` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '公众号openId',
  `userName` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户昵称',
  `userAvatar` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户头像',
  `userProfile` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户简介',
  `userRole` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'user' COMMENT '用户角色：user/admin/ban',
  `createTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `isDelete` tinyint(0) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1699657113955012611 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1699083758717698049, 'admin', 'ce476a83f73f01a574140f04fdf168b6', NULL, 'Rion', NULL, NULL, 'admin', '2023-09-05 23:35:10', '2023-09-07 10:38:57', 0);
INSERT INTO `user` VALUES (1699657113955012610, 'rion', 'b9d04182e35850172bf71c482d828329', NULL, 'rion', NULL, NULL, 'user', '2023-09-07 13:33:28', '2023-09-07 16:50:16', 0);

SET FOREIGN_KEY_CHECKS = 1;
