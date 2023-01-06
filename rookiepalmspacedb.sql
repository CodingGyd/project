-- phpMyAdmin SQL Dump
-- version 4.1.14.8
-- http://www.phpmyadmin.net
--
-- Host: 10.17.192.173:3306
-- Generation Time: 2018-06-16 23:09:51
-- 服务器版本： 5.6.23-baidu-rds-0.1.5.0-log
-- PHP Version: 5.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `rookiepalmspacedb`
--

-- --------------------------------------------------------

--
-- 表的结构 `adviceinfo`
--

CREATE TABLE IF NOT EXISTS `adviceinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text,
  `userid` int(11) NOT NULL,
  `time` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=11 ;

--
-- 转存表中的数据 `adviceinfo`
--

INSERT INTO `adviceinfo` (`id`, `content`, `userid`, `time`) VALUES
(8, '来', 1, '2016-12-25 10:28'),
(9, '哦哦哦', 1, '2016-12-25 10:28'),
(10, '哦哦', 1, '2016-12-25 10:28');

-- --------------------------------------------------------

--
-- 表的结构 `articleinfo`
--

CREATE TABLE IF NOT EXISTS `articleinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `title` text NOT NULL,
  `location` text,
  `type` int(11) NOT NULL COMMENT '技术1 生活2',
  `content` text,
  `time` text,
  `url` text,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=101 ;

--
-- 转存表中的数据 `articleinfo`
--

INSERT INTO `articleinfo` (`id`, `userid`, `title`, `location`, `type`, `content`, `time`, `url`) VALUES
(90, 1, '困', '上海市浦东新区鹤永路靠近好药师大药房(鹤波药店)', 2, '测测', '1482600331499', NULL),
(99, 1, '爬到', '上海市浦东新区鹤永路靠近好药师大药房(鹤波药店)', 1, '测测', '1482654650730', NULL);

-- --------------------------------------------------------

--
-- 表的结构 `h_user_info`
--

CREATE TABLE IF NOT EXISTS `h_user_info` (
  `UID` varchar(30) NOT NULL COMMENT '用户ID',
  `USERNAME` varchar(50) NOT NULL COMMENT '用户名',
  `PASSWORD` varchar(255) NOT NULL COMMENT '密码',
  `EMAIL` varchar(255) NOT NULL COMMENT 'email',
  `SEX` char(1) NOT NULL DEFAULT '0' COMMENT '性别，0-男 1-女',
  `PHOTO` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `ACTIVE` char(1) NOT NULL DEFAULT '0' COMMENT '激活状态 0-未激活 1-激活',
  `REG_TIME` timestamp NOT NULL COMMENT '创建时间',
  `MOD_TIME` timestamp NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`UID`),
  UNIQUE KEY `uc_name` (`USERNAME`),
  UNIQUE KEY `uc_email` (`EMAIL`),
  KEY `ix_name_pwd` (`USERNAME`,`PASSWORD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户基本信息表';

--
-- 转存表中的数据 `h_user_info`
--

INSERT INTO `h_user_info` (`UID`, `USERNAME`, `PASSWORD`, `EMAIL`, `SEX`, `PHOTO`, `ACTIVE`, `REG_TIME`, `MOD_TIME`) VALUES
('1', '1', '1', '1', '0', '1', '0', '2017-10-17 10:12:17', '2017-10-17 10:12:17'),
('2', '2', '2', '2@qq.com', '0', NULL, '0', '2017-11-16 02:36:23', '2017-11-16 02:36:23');

-- --------------------------------------------------------

--
-- 表的结构 `imageinfo`
--

CREATE TABLE IF NOT EXISTS `imageinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `type` int(255) NOT NULL COMMENT '家人1 朋友2自拍3搞笑4旅游5工作6',
  `time` varchar(255) NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `url` text NOT NULL,
  `userId` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=36 ;

-- --------------------------------------------------------

--
-- 表的结构 `mine_articles`
--

CREATE TABLE IF NOT EXISTS `mine_articles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `descs` text NOT NULL COMMENT '文章概述',
  `url` text,
  `content` text NOT NULL,
  `htmlContent` text NOT NULL,
  `updatetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `readingcount` int(11) NOT NULL DEFAULT '0',
  `type` int(11) NOT NULL COMMENT '分类代码,关联系统常量表获取名称',
  `praisecount` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='mine工程-文章信息表' AUTO_INCREMENT=80 ;
 
 
-- 转存表中的数据 `mine_articles`
--

INSERT INTO `mine_articles` (`id`, `title`, `descs`, `url`, `content`, `htmlContent`, `updatetime`, `readingcount`, `type`) VALUES
(27, 'sqlserver数据库数据类型与java数据类型对应表', 'sqlserver数据库数据类型与java数据类型对应表', NULL, '###sqlserver数据库数据类型与java数据类型对应表\n\n|  编号 |  数据库类型 |JDBC类型|JDBC索引\n| ------------ | ------------ |\n|1|int|java.lang.Integer|4\n|2|varchar|java.lang.String|12\n|3|char|java.lang.String|1\n|4|nchar|java.lang.String |1\n|5|nvarchar|java.lang.String |12\n|6|text|java.lang.String|-1\n|7|ntext|java.lang.String |-1\n|8|tinyint|java.lang.Integer |-6\n|9|int|java.lang.Integer |4\n|10|tinyint|java.lang.Integer|-6\n|11|smallint|java.lang.Integer|5\n|12|bit|java.lang.Boolean|-7\n|13|bigint|java.lang.Long|-5\n|14|float|java.lang.Double |6\n|15|decimal|java.math.BigDecimal|3\n|16|money|java.math.BigDecimal|3\n|17|smallmoney|java.math.BigDecimal|3\n|18|numeric|java.math.BigDecimal|2\n|19|real|java.lang.Float |7\n|20|uniqueidentifier|java.lang.String |1\n|21|smalldatetime|java.sql.Timestamp |93\n|22|datetime |java.sql.Timestamp|93\n|23|timestamp |byte[]|-2\n|24|binary|byte[] |-2\n|25|varbinary |byte[]|-3\n|26|image |byte[]|-4\n|27|sql_variant|java.lang.String|12\n', '<h3 id="h3-sqlserver-java-"><a name="sqlserver数据库数据类型与java数据类型对应表" class="reference-link"></a><span class="header-link octicon octicon-link"></span>sqlserver数据库数据类型与java数据类型对应表</h3><table>\n<thead>\n<tr>\n<th>编号</th>\n<th>数据库类型</th>\n<th>JDBC类型</th>\n<th>JDBC索引</th>\n</tr>\n</thead>\n<tbody>\n<tr>\n<td>1</td>\n<td>int</td>\n<td>java.lang.Integer</td>\n<td>4</td>\n</tr>\n<tr>\n<td>2</td>\n<td>varchar</td>\n<td>java.lang.String</td>\n<td>12</td>\n</tr>\n<tr>\n<td>3</td>\n<td>char</td>\n<td>java.lang.String</td>\n<td>1</td>\n</tr>\n<tr>\n<td>4</td>\n<td>nchar</td>\n<td>java.lang.String</td>\n<td>1</td>\n</tr>\n<tr>\n<td>5</td>\n<td>nvarchar</td>\n<td>java.lang.String</td>\n<td>12</td>\n</tr>\n<tr>\n<td>6</td>\n<td>text</td>\n<td>java.lang.String</td>\n<td>-1</td>\n</tr>\n<tr>\n<td>7</td>\n<td>ntext</td>\n<td>java.lang.String</td>\n<td>-1</td>\n</tr>\n<tr>\n<td>8</td>\n<td>tinyint</td>\n<td>java.lang.Integer</td>\n<td>-6</td>\n</tr>\n<tr>\n<td>9</td>\n<td>int</td>\n<td>java.lang.Integer</td>\n<td>4</td>\n</tr>\n<tr>\n<td>10</td>\n<td>tinyint</td>\n<td>java.lang.Integer</td>\n<td>-6</td>\n</tr>\n<tr>\n<td>11</td>\n<td>smallint</td>\n<td>java.lang.Integer</td>\n<td>5</td>\n</tr>\n<tr>\n<td>12</td>\n<td>bit</td>\n<td>java.lang.Boolean</td>\n<td>-7</td>\n</tr>\n<tr>\n<td>13</td>\n<td>bigint</td>\n<td>java.lang.Long</td>\n<td>-5</td>\n</tr>\n<tr>\n<td>14</td>\n<td>float</td>\n<td>java.lang.Double</td>\n<td>6</td>\n</tr>\n<tr>\n<td>15</td>\n<td>decimal</td>\n<td>java.math.BigDecimal</td>\n<td>3</td>\n</tr>\n<tr>\n<td>16</td>\n<td>money</td>\n<td>java.math.BigDecimal</td>\n<td>3</td>\n</tr>\n<tr>\n<td>17</td>\n<td>smallmoney</td>\n<td>java.math.BigDecimal</td>\n<td>3</td>\n</tr>\n<tr>\n<td>18</td>\n<td>numeric</td>\n<td>java.math.BigDecimal</td>\n<td>2</td>\n</tr>\n<tr>\n<td>19</td>\n<td>real</td>\n<td>java.lang.Float</td>\n<td>7</td>\n</tr>\n<tr>\n<td>20</td>\n<td>uniqueidentifier</td>\n<td>java.lang.String</td>\n<td>1</td>\n</tr>\n<tr>\n<td>21</td>\n<td>smalldatetime</td>\n<td>java.sql.Timestamp</td>\n<td>93</td>\n</tr>\n<tr>\n<td>22</td>\n<td>datetime</td>\n<td>java.sql.Timestamp</td>\n<td>93</td>\n</tr>\n<tr>\n<td>23</td>\n<td>timestamp</td>\n<td>byte[]</td>\n<td>-2</td>\n</tr>\n<tr>\n<td>24</td>\n<td>binary</td>\n<td>byte[]</td>\n<td>-2</td>\n</tr>\n<tr>\n<td>25</td>\n<td>varbinary</td>\n<td>byte[]</td>\n<td>-3</td>\n</tr>\n<tr>\n<td>26</td>\n<td>image</td>\n<td>byte[]</td>\n<td>-4</td>\n</tr>\n<tr>\n<td>27</td>\n<td>sql_variant</td>\n<td>java.lang.String</td>\n<td>12</td>\n</tr>\n</tbody>\n</table>\n', '2017-11-01 22:43:50', 21175, 3),
(28, '立正向前走', '立正向前走', NULL, '### 立正向前走\n&nbsp;&nbsp;&nbsp;我们总有这样的茫然时刻，风霜雪雨中，我提着自己的手提箱，问自己我要往哪去?该回去还是继续向前?此时，我们知道应该赶路，却不得不停下来，因为这一刻意义太重大，一念之间决定了我们以后的路。\n\n \n\n&nbsp;&nbsp;&nbsp;走到生命的哪一个阶段，都该喜欢那一段时光，完成那一阶段该完成的职责，顺生而行，不沉迷过去，不狂热地期待着未来，生命这样就好。不管正经历着怎样的挣扎与挑战，或许我们都只有一个选择：虽然痛苦，却依然要快乐，并相信未来。\n\n \n\n&nbsp;&nbsp;&nbsp;世上最凄绝的距离是两个人本来距离很远，互不相识，忽然有一天，他们相识，相爱，距离变得很近。然后有一天，不再相爱了，本来很近的两个人，变得很远，甚至比以前更远。\n\n \n\n&nbsp;&nbsp;&nbsp;我不习惯把自己心里的痛苦展示给别人，因为我怕别人安慰，也不想得到怜悯。相比于弱者的倾诉和哭泣，我更喜欢强者的骄傲和被仰望。心里乌云密布，面上不动声色。就算被误解，也不澄清，不解释，不在乎，把这些都当前行的力量。\n\n \n\n&nbsp;&nbsp;&nbsp;为什么我们总是不懂得珍惜眼前人?在未可预知的重逢里，我们以为总会重逢，总会有缘再会，总以为有机会说一声对不起，却从没想过每一次挥手道别，都可能是诀别，每一声叹息，都可能是人间最后的一声叹息。\n\n \n\n&nbsp;&nbsp;&nbsp;没有誓言的爱情才是最可靠的爱情。爱情是与誓言无关的。但有趣的是，大多女人都喜欢听男人的誓言，即便是在心里并不把男人的誓言当真，也是听着幸福无比。\n\n \n\n&nbsp;&nbsp;&nbsp;如果生活是一杯水，那么痛苦就是掉落杯中的灰尘。我们可以选择让心静下来，慢慢沉淀那些痛苦。如果总是不断地去搅和，痛苦就会充满我们的生活。\n\n \n\n&nbsp;&nbsp;&nbsp;年轻时，我不要你们心如止水，我要你们妖孽横行。我不要你们收敛锋芒，我要你们跋扈张扬。我不要你们平淡青春，我要你们嚣张挥霍。总之，要笑要哭，要爱要痛。因为很久很久以后，这段光芒万丈的回忆，会帮你度过很多很多，你以为不能度过的苦难时期。\n\n \n\n&nbsp;&nbsp;&nbsp;你的脆弱，也是你的坚强。\n\n \n\n&nbsp;&nbsp;&nbsp;爱情如此使人着迷，是不是正因为它是靠不住的?明知道它是水，是无根的，我们却用一双手和一双脚想要去拦住它。直到一天，当它翻起的波涛差点儿把我们淹没，我们才发现，即使再多出十双手和十双脚，要走的东西，终究是拦不住的。\n\n \n\n&nbsp;&nbsp;&nbsp;当初你有胆量去选，同样该有勇气把后果承受。\n\n \n\n&nbsp;&nbsp;&nbsp;很多时候，面对那些原本以为过不去的坎，面对那些一时无法接受的事，我们能做的只是沉默，沉默的等这一阵过去。相信这一夜的痛哭过后，还有新的早晨。活在世上遇到的悲伤是很多的，但欢乐也有。即使欢乐的日子比哭泣的日子少太多，这个世界，仍值得我们为之走一遭。\n\n \n\n&nbsp;&nbsp;&nbsp;在寂静中我唯一能听到的，只剩下那些我说不出来的话。我爱你，一直很爱你，以后也是。\n\n \n\n&nbsp;&nbsp;&nbsp;人们不解释的主要原因是根本不在乎对方的想法，无关重要的人，对无关重要的事有点儿误会，有什么关系。你信也好，不信也好，都于当事人生活毫无影响，何劳解释。\n\n \n\n&nbsp;&nbsp;&nbsp;既然一事无成，那你还有什么好失去的，鼓起勇气面对一切难堪，打败它吧!只要不放弃一切幸福的信念，我相信，一定会有好事发生!\n\n \n\n&nbsp;&nbsp;&nbsp;有些时候我觉得很好笑，那些在我们记忆里占据很小一部分的人，你竟然一辈子都忘不掉。\n\n \n\n    人生太短，所以笑吧，趁你现在还有牙齿时。\n\n \n\n&nbsp;&nbsp;&nbsp;我们不怕目标定得高远，只怕没有追寻的勇气、热情和执著。只要心头时时燃烧着坚定的信念，一往无前地行进下去，就会惊讶地发现：很多所谓的远方其实并不遥远。\n\n \n\n&nbsp;&nbsp;&nbsp;一个人总有一天会明白，忌妒是无用的，而模仿他人无异于自杀。因为不论好坏，人只有自己才能帮助自己，只有耕种自己的田地，才能收获自家的玉米。', '<h3 id="h3-u7ACBu6B63u5411u524Du8D70"><a name="立正向前走" class="reference-link"></a><span class="header-link octicon octicon-link"></span>立正向前走</h3><p>&nbsp;&nbsp;&nbsp;我们总有这样的茫然时刻，风霜雪雨中，我提着自己的手提箱，问自己我要往哪去?该回去还是继续向前?此时，我们知道应该赶路，却不得不停下来，因为这一刻意义太重大，一念之间决定了我们以后的路。</p>\n<p>&nbsp;&nbsp;&nbsp;走到生命的哪一个阶段，都该喜欢那一段时光，完成那一阶段该完成的职责，顺生而行，不沉迷过去，不狂热地期待着未来，生命这样就好。不管正经历着怎样的挣扎与挑战，或许我们都只有一个选择：虽然痛苦，却依然要快乐，并相信未来。</p>\n<p>&nbsp;&nbsp;&nbsp;世上最凄绝的距离是两个人本来距离很远，互不相识，忽然有一天，他们相识，相爱，距离变得很近。然后有一天，不再相爱了，本来很近的两个人，变得很远，甚至比以前更远。</p>\n<p>&nbsp;&nbsp;&nbsp;我不习惯把自己心里的痛苦展示给别人，因为我怕别人安慰，也不想得到怜悯。相比于弱者的倾诉和哭泣，我更喜欢强者的骄傲和被仰望。心里乌云密布，面上不动声色。就算被误解，也不澄清，不解释，不在乎，把这些都当前行的力量。</p>\n<p>&nbsp;&nbsp;&nbsp;为什么我们总是不懂得珍惜眼前人?在未可预知的重逢里，我们以为总会重逢，总会有缘再会，总以为有机会说一声对不起，却从没想过每一次挥手道别，都可能是诀别，每一声叹息，都可能是人间最后的一声叹息。</p>\n<p>&nbsp;&nbsp;&nbsp;没有誓言的爱情才是最可靠的爱情。爱情是与誓言无关的。但有趣的是，大多女人都喜欢听男人的誓言，即便是在心里并不把男人的誓言当真，也是听着幸福无比。</p>\n<p>&nbsp;&nbsp;&nbsp;如果生活是一杯水，那么痛苦就是掉落杯中的灰尘。我们可以选择让心静下来，慢慢沉淀那些痛苦。如果总是不断地去搅和，痛苦就会充满我们的生活。</p>\n<p>&nbsp;&nbsp;&nbsp;年轻时，我不要你们心如止水，我要你们妖孽横行。我不要你们收敛锋芒，我要你们跋扈张扬。我不要你们平淡青春，我要你们嚣张挥霍。总之，要笑要哭，要爱要痛。因为很久很久以后，这段光芒万丈的回忆，会帮你度过很多很多，你以为不能度过的苦难时期。</p>\n<p>&nbsp;&nbsp;&nbsp;你的脆弱，也是你的坚强。</p>\n<p>&nbsp;&nbsp;&nbsp;爱情如此使人着迷，是不是正因为它是靠不住的?明知道它是水，是无根的，我们却用一双手和一双脚想要去拦住它。直到一天，当它翻起的波涛差点儿把我们淹没，我们才发现，即使再多出十双手和十双脚，要走的东西，终究是拦不住的。</p>\n<p>&nbsp;&nbsp;&nbsp;当初你有胆量去选，同样该有勇气把后果承受。</p>\n<p>&nbsp;&nbsp;&nbsp;很多时候，面对那些原本以为过不去的坎，面对那些一时无法接受的事，我们能做的只是沉默，沉默的等这一阵过去。相信这一夜的痛哭过后，还有新的早晨。活在世上遇到的悲伤是很多的，但欢乐也有。即使欢乐的日子比哭泣的日子少太多，这个世界，仍值得我们为之走一遭。</p>\n<p>&nbsp;&nbsp;&nbsp;在寂静中我唯一能听到的，只剩下那些我说不出来的话。我爱你，一直很爱你，以后也是。</p>\n<p>&nbsp;&nbsp;&nbsp;人们不解释的主要原因是根本不在乎对方的想法，无关重要的人，对无关重要的事有点儿误会，有什么关系。你信也好，不信也好，都于当事人生活毫无影响，何劳解释。</p>\n<p>&nbsp;&nbsp;&nbsp;既然一事无成，那你还有什么好失去的，鼓起勇气面对一切难堪，打败它吧!只要不放弃一切幸福的信念，我相信，一定会有好事发生!</p>\n<p>&nbsp;&nbsp;&nbsp;有些时候我觉得很好笑，那些在我们记忆里占据很小一部分的人，你竟然一辈子都忘不掉。</p>\n<pre><code>人生太短，所以笑吧，趁你现在还有牙齿时。\n</code></pre><p>&nbsp;&nbsp;&nbsp;我们不怕目标定得高远，只怕没有追寻的勇气、热情和执著。只要心头时时燃烧着坚定的信念，一往无前地行进下去，就会惊讶地发现：很多所谓的远方其实并不遥远。</p>\n<p>&nbsp;&nbsp;&nbsp;一个人总有一天会明白，忌妒是无用的，而模仿他人无异于自杀。因为不论好坏，人只有自己才能帮助自己，只有耕种自己的田地，才能收获自家的玉米。</p>\n', '2017-11-01 20:15:16', 21333, 2),
(33, '资料网站合集(持续更新中...)', '这里记录博主平常学习时会逛的IT资讯、工具网站地址', NULL, '## 1. [ImportNew-专注于 Java 技术分享的网站](http://www.importnew.com/ "ImportNew-专注于 Java 技术分享的网站")\n\n## 2. [ 	GitHub-开源及私有软件项目的大型托管平台](https://github.com " 	GitHub-开源及私有软件项目的大型托管平台")\n\n## 3. [CSDN-全球最大中文IT社区](http://www.csdn.net/ "CSDN-全球最大中文IT社区")\n\n## 4. [ITeye-Java编程 Spring框架 Ajax技术 agile敏捷软件](http://www.iteye.com/ "ITeye-Java编程 Spring框架 Ajax技术 agile敏捷软件")\n\n## 5. [Spring Cloud中国社区论坛](http://bbs.springcloud.cn/ "Spring Cloud中国社区论坛")\n\n## 6. [伯乐在线-专业的互联网职业社区](http://www.jobbole.com/ "伯乐在线-专业的互联网职业社区")\n\n## 7. [51CTO-专注IT技术领域，打造中国领先的IT技术网络平台](http://www.51cto.com/ "51CTO-专注IT技术领域，打造中国领先的IT技术网络平台")\n\n## 8. [开源中国](https://www.oschina.net/ "开源中国")\n\n## 9. [JSON在线解析](http://www.sojson.com/ "JSON在线解析")\n\n## 10.[Hutool-Java工具集，针对项目中util包进行开刀，抽象大量的工具方法，旨在减少项目中工具类的数量，将我们的编码工作专注在业务上。](http://hutool.cn/ "Hutool-Java工具集，针对项目中util包进行开刀，抽象大量的工具方法，旨在减少项目中工具类的数量，将我们的编码工作专注在业务上。")\n\n## 11.[小众搜索引擎](https://www.ecosia.org/ "小众搜索引擎")\n## 12.[小众搜索引擎](https://so.mezw.com/ "小众搜索引擎")\n## 13.[小众搜索引擎](http://bird.so "小众搜索引擎")\n## 14. [博客园](https://www.cnblogs.com/ "博客园")\n', '<h2 id="h2-1-importnew-java-"><a name="1.   ImportNew-专注于 Java 技术分享的网站" class="reference-link"></a><span class="header-link octicon octicon-link"></span>1. <a href="http://www.importnew.com/" title="ImportNew-专注于 Java 技术分享的网站">ImportNew-专注于 Java 技术分享的网站</a></h2><h2 id="h2-2-github-"><a name="2.        GitHub-开源及私有软件项目的大型托管平台" class="reference-link"></a><span class="header-link octicon octicon-link"></span>2. <a href="https://github.com" title="     GitHub-开源及私有软件项目的大型托管平台">     GitHub-开源及私有软件项目的大型托管平台</a></h2><h2 id="h2-3-csdn-it-"><a name="3.   CSDN-全球最大中文IT社区" class="reference-link"></a><span class="header-link octicon octicon-link"></span>3. <a href="http://www.csdn.net/" title="CSDN-全球最大中文IT社区">CSDN-全球最大中文IT社区</a></h2><h2 id="h2-4-iteye-java-spring-ajax-agile-"><a name="4.   ITeye-Java编程 Spring框架 Ajax技术 agile敏捷软件" class="reference-link"></a><span class="header-link octicon octicon-link"></span>4. <a href="http://www.iteye.com/" title="ITeye-Java编程 Spring框架 Ajax技术 agile敏捷软件">ITeye-Java编程 Spring框架 Ajax技术 agile敏捷软件</a></h2><h2 id="h2-5-spring-cloud-"><a name="5.   Spring Cloud中国社区论坛" class="reference-link"></a><span class="header-link octicon octicon-link"></span>5. <a href="http://bbs.springcloud.cn/" title="Spring Cloud中国社区论坛">Spring Cloud中国社区论坛</a></h2><h2 id="h2-6-"><a name="6.   伯乐在线-专业的互联网职业社区" class="reference-link"></a><span class="header-link octicon octicon-link"></span>6. <a href="http://www.jobbole.com/" title="伯乐在线-专业的互联网职业社区">伯乐在线-专业的互联网职业社区</a></h2><h2 id="h2-7-51cto-it-it-"><a name="7.   51CTO-专注IT技术领域，打造中国领先的IT技术网络平台" class="reference-link"></a><span class="header-link octicon octicon-link"></span>7. <a href="http://www.51cto.com/" title="51CTO-专注IT技术领域，打造中国领先的IT技术网络平台">51CTO-专注IT技术领域，打造中国领先的IT技术网络平台</a></h2><h2 id="h2-8-"><a name="8.   开源中国" class="reference-link"></a><span class="header-link octicon octicon-link"></span>8. <a href="https://www.oschina.net/" title="开源中国">开源中国</a></h2><h2 id="h2-9-json-"><a name="9.   JSON在线解析" class="reference-link"></a><span class="header-link octicon octicon-link"></span>9. <a href="http://www.sojson.com/" title="JSON在线解析">JSON在线解析</a></h2><h2 id="h2-10-hutool-java-util-"><a name="10.  Hutool-Java工具集，针对项目中util包进行开刀，抽象大量的工具方法，旨在减少项目中工具类的数量，将我们的编码工作专注在业务上。" class="reference-link"></a><span class="header-link octicon octicon-link"></span>10.<a href="http://hutool.cn/" title="Hutool-Java工具集，针对项目中util包进行开刀，抽象大量的工具方法，旨在减少项目中工具类的数量，将我们的编码工作专注在业务上。">Hutool-Java工具集，针对项目中util包进行开刀，抽象大量的工具方法，旨在减少项目中工具类的数量，将我们的编码工作专注在业务上。</a></h2><h2 id="h2-11-"><a name="11.  小众搜索引擎" class="reference-link"></a><span class="header-link octicon octicon-link"></span>11.<a href="https://www.ecosia.org/" title="小众搜索引擎">小众搜索引擎</a></h2><h2 id="h2-12-"><a name="12.  小众搜索引擎" class="reference-link"></a><span class="header-link octicon octicon-link"></span>12.<a href="https://so.mezw.com/" title="小众搜索引擎">小众搜索引擎</a></h2><h2 id="h2-13-"><a name="13.  小众搜索引擎" class="reference-link"></a><span class="header-link octicon octicon-link"></span>13.<a href="http://bird.so" title="小众搜索引擎">小众搜索引擎</a></h2><h2 id="h2-14-"><a name="14.   博客园" class="reference-link"></a><span class="header-link octicon octicon-link"></span>14. <a href="https://www.cnblogs.com/" title="博客园">博客园</a></h2>', '2018-03-23 09:00:35', 21128, 1),
(35, '个人博客版本更新记录(持续更新中...)', '这里记录博主开发本博客的版本更新记录', NULL, '#### 前言\n    一直以来都想开发出一个属于自己的博客网站，可惜总会因为各种原因没有去动手实现，可能是因为懒吧。如今断断续续地终于开发出了博客的V1.0版本，界面比较简陋。后续我会再花零散的时间对本博客进行不断改造升级，并在此博文中记录每个版本更新的功能点。\n\n#### 开发技术\n	本博客网站采用前端和后端完全分离、后端按业务点进行模块分离的思想进行开发.\n	前端技术：html+css+javascript+jquery+bootstrap+markdown.\n	后端技术：springboot+mybatis+redis+druid.\n\n#### V1.0\n	完成前后端基础核心架构搭建开发.\n	完成文章列表分页展示、最新文章、随机文章、随笔展示功能点开发.\n', '', '2017-11-01 22:40:58', 21282, 3),
(36, '个人博客网站开发-域名备案流程', '简单记录一下本网站从域名申请到可以直接通过域名访问所需要完成的一些操作', NULL, '## 前言\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一般某个网站程序部署到云服务器上后,我们就可以直接通过"服务器主机IP:应用端口"的方式来访问该网站服务了，但这种方式非常不友好。对于广大用户而言，没几个人会记得住那一串IP数字地址。因此我们需要提供一个友好的名称来作为服务器主机IP的别名，让用户在浏览器输入别名就可以访问到网站服务，这个别名就可以理解为域名(域名具体介绍请自行百度)。下面简单记录下本博客网站从申请域名->备案->做IP解析、端口转发的过程。\n\n###### 一、申请域名以及实名认证\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;我是在百度云的域名服务注册中心进行域名申请注册的，地址请戳[域名申请地址](https://cloud.baidu.com/product/bcd/search.html "域名申请地址")。域名具体申请过程就不说了，进入百度云域名服务注册中心自己看。本人在申请域名的时候想了很多个，但是发现都被注册掉了，无奈，，最后选了个二般的域名进行注册。注册完后在百度云平台上完成实名认证操作。\n\n###### 二、工信部备案\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;以前域名备案要去当地的管局进行，现在可以直接在比如百度云、阿里云上进行，还是很方便的。我的域名是在百度云上完成备案流程的，备案地址戳[百度云备案系统](http://beian.bce.baidu.com/ "百度云备案系统")，备案流程也不是很复杂，按备案系统操作步骤慢慢来即可完   成。\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;工信部备案这里耗时最长，我的网站大概半个月才备案完成。\n\n###### 三、域名解析、端口转发\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;域名解析我也是直接在百度云域名服务控制台上完成的，几分钟就好了。需要填写主机记录、记录类型、解析线路、记录值、TTL这几个选项，具体含义请自行百度。这里需要注意“记录值”就是服务器主机的IP地址，不能填写具体的端口号。默认在浏览器通过域名访问时会解析到域名对应的IP地址的80端口上。如果需要域名访问服务器上非80端口，可以通过域名:端口号的方式来完成，或者通过80端口转发来达到目的。一般我们都选择第二种方式，域名访问时无需额外填写端口信息即可访问到网站，这种方式更加友好。\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;我是通过用nginx来监听域名指向的默认80端口并转发到网站程序运行的xxxx端口来完成域名访问网站的。至于nginx如何设置端口转发，非常简单，改下配置文件重启nginx即可!具体操作步骤自行百度教程。\n\n###### 四、通过域名进行网站访问\n完成前面三个步骤，就可以打开浏览器通过域名来访问我们自己写的网站了，也可以把域名分享给互联网上的小伙伴们，与小伙伴们分享自己所思、所想。\n\n\n', '<h2 id="h2-u524Du8A00"><a name="前言" class="reference-link"></a><span class="header-link octicon octicon-link"></span>前言</h2><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一般某个网站程序部署到云服务器上后,我们就可以直接通过”服务器主机IP:应用端口”的方式来访问该网站服务了，但这种方式非常不友好。对于广大用户而言，没几个人会记得住那一串IP数字地址。因此我们需要提供一个友好的名称来作为服务器主机IP的别名，让用户在浏览器输入别名就可以访问到网站服务，这个别名就可以理解为域名(域名具体介绍请自行百度)。下面简单记录下本博客网站从申请域名-&gt;备案-&gt;做IP解析、端口转发的过程。</p>\n<h6 id="h6--"><a name="一、申请域名以及实名认证" class="reference-link"></a><span class="header-link octicon octicon-link"></span>一、申请域名以及实名认证</h6><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;我是在百度云的域名服务注册中心进行域名申请注册的，地址请戳<a href="https://cloud.baidu.com/product/bcd/search.html" title="域名申请地址">域名申请地址</a>。域名具体申请过程就不说了，进入百度云域名服务注册中心自己看。本人在申请域名的时候想了很多个，但是发现都被注册掉了，无奈，，最后选了个二般的域名进行注册。注册完后在百度云平台上完成实名认证操作。</p>\n<h6 id="h6--"><a name="二、工信部备案" class="reference-link"></a><span class="header-link octicon octicon-link"></span>二、工信部备案</h6><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;以前域名备案要去当地的管局进行，现在可以直接在比如百度云、阿里云上进行，还是很方便的。我的域名是在百度云上完成备案流程的，备案地址戳<a href="http://beian.bce.baidu.com/" title="百度云备案系统">百度云备案系统</a>，备案流程也不是很复杂，按备案系统操作步骤慢慢来即可完   成。<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;工信部备案这里耗时最长，我的网站大概半个月才备案完成。</p>\n<h6 id="h6--"><a name="三、域名解析、端口转发" class="reference-link"></a><span class="header-link octicon octicon-link"></span>三、域名解析、端口转发</h6><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;域名解析我也是直接在百度云域名服务控制台上完成的，几分钟就好了。需要填写主机记录、记录类型、解析线路、记录值、TTL这几个选项，具体含义请自行百度。这里需要注意“记录值”就是服务器主机的IP地址，不能填写具体的端口号。默认在浏览器通过域名访问时会解析到域名对应的IP地址的80端口上。如果需要域名访问服务器上非80端口，可以通过域名:端口号的方式来完成，或者通过80端口转发来达到目的。一般我们都选择第二种方式，域名访问时无需额外填写端口信息即可访问到网站，这种方式更加友好。<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;我是通过用nginx来监听域名指向的默认80端口并转发到网站程序运行的xxxx端口来完成域名访问网站的。至于nginx如何设置端口转发，非常简单，改下配置文件重启nginx即可!具体操作步骤自行百度教程。</p>\n<h6 id="h6--"><a name="四、通过域名进行网站访问" class="reference-link"></a><span class="header-link octicon octicon-link"></span>四、通过域名进行网站访问</h6><p>完成前面三个步骤，就可以打开浏览器通过域名来访问我们自己写的网站了，也可以把域名分享给互联网上的小伙伴们，与小伙伴们分享自己所思、所想。</p>\n', '2017-11-01 22:55:53', 21219, 3),
(43, '测', '测11111', NULL, ' 测测vcccc', '<p> 测测vcccc</p>\n', '2017-10-30 14:47:01', 0, 233),
(54, 'AA', 'AA', NULL, '![](http://www.gydblog.com:8888/group1/M00/00/00/wKgACFn99DGAcVA_AABTi3cOz1U711.png)', '<p><img src="http://www.gydblog.com:8888/group1/M00/00/00/wKgACFn99DGAcVA_AABTi3cOz1U711.png" alt=""></p>\n', '2017-11-05 01:09:08', 0, -1),
(78, '分布式文件系统FastDFS《一》', '博客文章需要用到图片文件，图片资源是保存在单独的文件服务器上。本博客的文件服务器是由FastDFS搭建的。FastDFS是一个开源的轻量级分布式文件系统，它对文件进行管理，功能包括：文件存储、文件同步、文件访问（文件上传、文件下载）等，解决了大容量存储和负载均衡的问题。特别适合以文件为载体的在线服务，如相册网站、视频网站等等。', NULL, '## FastDFS\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;FastDFS是一个开源的轻量级分布式文件系统，它对文件进行管理，功能包括：文件存储、文件同步、文件访问（文件上传、文件下载）等，解决了大容量存储和负载均衡的问题。特别适合以文件为载体的在线服务，如相册网站、视频网站等等。\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;FastDFS为互联网量身定制，充分考虑了冗余备份、负载均衡、线性扩容等机制，并注重高可用、高性能等指标，使用FastDFS很容易搭建一套高性能的文件服务器集群提供文件上传、下载等服务。\n\n## 简介\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;FastDFS服务端有两个角色：跟踪器（tracker）和存储节点（storage）。跟踪器主要做调度工作，在访问上起负载均衡的作用。\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;存储节点存储文件，完成文件管理的所有功能：就是这样的存储、同步和提供存取接口，FastDFS同时对文件的metadata进行管理。所谓文件的meta data就是文件的相关属性，以键值对（key valuepair）方式表示，如：width=1024，其中的key为width，value为1024。文件metadata是文件属性列表，可以包含多个键值对。\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;跟踪器和存储节点都可以由一台或多台服务器构成。跟踪器和存储节点中的服务器均可以随时增加或下线而不会影响线上服务。其中跟踪器中的所有服务器都是对等的，可以根据服务器的压力情况随时增加或减少。\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;为了支持大容量，存储节点（服务器）采用了分卷（或分组）的组织方式。存储系统由一个或多个卷组成，卷与卷之间的文件是相互独立的，所有卷的文件容量累加就是整个存储系统中的文件容量。一个卷可以由一台或多台存储服务器组成，一个卷下的存储服务器中的文件都是相同的，卷中的多台存储服务器起到了冗余备份和负载均衡的作用。\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;在卷中增加服务器时，同步已有的文件由系统自动完成，同步完成后，系统自动将新增服务器切换到线上提供服务。\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当存储空间不足或即将耗尽时，可以动态添加卷。只需要增加一台或多台服务器，并将它们配置为一个新的卷，这样就扩大了存储系统的容量。\nFastDFS中的文件标识分为两个部分：卷名和文件名，二者缺一不可。\n![](http://www.gydblog.com:8888/group1/M00/00/00/wKgACFoAWdeAG_M4AASfYzbioyg760.png)\n\n## 上传文件交互过程\n1. client询问tracker上传到的storage，不需要附加参数；\n2. tracker返回一台可用的storage；\n3. client直接和storage通讯完成文件上传。\nFastDFS file download\n\n##下载交互过程\n1. client询问tracker下载文件的storage，参数为文件标识（卷名和文件名）；\n2. tracker返回一台可用的storage；\n3. client直接和storage通讯完成文件下载。\n需要说明的是，client为使用FastDFS服务的调用方，client也应该是一台服务器，它对tracker和storage的调用均为服务器间的调用。\n', '<h2 id="h2-fastdfs"><a name="FastDFS" class="reference-link"></a><span class="header-link octicon octicon-link"></span>FastDFS</h2><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;FastDFS是一个开源的轻量级分布式文件系统，它对文件进行管理，功能包括：文件存储、文件同步、文件访问（文件上传、文件下载）等，解决了大容量存储和负载均衡的问题。特别适合以文件为载体的在线服务，如相册网站、视频网站等等。<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;FastDFS为互联网量身定制，充分考虑了冗余备份、负载均衡、线性扩容等机制，并注重高可用、高性能等指标，使用FastDFS很容易搭建一套高性能的文件服务器集群提供文件上传、下载等服务。</p>\n<h2 id="h2-u7B80u4ECB"><a name="简介" class="reference-link"></a><span class="header-link octicon octicon-link"></span>简介</h2><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;FastDFS服务端有两个角色：跟踪器（tracker）和存储节点（storage）。跟踪器主要做调度工作，在访问上起负载均衡的作用。<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;存储节点存储文件，完成文件管理的所有功能：就是这样的存储、同步和提供存取接口，FastDFS同时对文件的metadata进行管理。所谓文件的meta data就是文件的相关属性，以键值对（key valuepair）方式表示，如：width=1024，其中的key为width，value为1024。文件metadata是文件属性列表，可以包含多个键值对。<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;跟踪器和存储节点都可以由一台或多台服务器构成。跟踪器和存储节点中的服务器均可以随时增加或下线而不会影响线上服务。其中跟踪器中的所有服务器都是对等的，可以根据服务器的压力情况随时增加或减少。<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;为了支持大容量，存储节点（服务器）采用了分卷（或分组）的组织方式。存储系统由一个或多个卷组成，卷与卷之间的文件是相互独立的，所有卷的文件容量累加就是整个存储系统中的文件容量。一个卷可以由一台或多台存储服务器组成，一个卷下的存储服务器中的文件都是相同的，卷中的多台存储服务器起到了冗余备份和负载均衡的作用。<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;在卷中增加服务器时，同步已有的文件由系统自动完成，同步完成后，系统自动将新增服务器切换到线上提供服务。<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当存储空间不足或即将耗尽时，可以动态添加卷。只需要增加一台或多台服务器，并将它们配置为一个新的卷，这样就扩大了存储系统的容量。<br>FastDFS中的文件标识分为两个部分：卷名和文件名，二者缺一不可。<br><img src="http://www.gydblog.com:8888/group1/M00/00/00/wKgACFoAWdeAG_M4AASfYzbioyg760.png" alt=""></p>\n<h2 id="h2-u4E0Au4F20u6587u4EF6u4EA4u4E92u8FC7u7A0B"><a name="上传文件交互过程" class="reference-link"></a><span class="header-link octicon octicon-link"></span>上传文件交互过程</h2><ol>\n<li>client询问tracker上传到的storage，不需要附加参数；</li><li>tracker返回一台可用的storage；</li><li>client直接和storage通讯完成文件上传。<br>FastDFS file download</li></ol>\n<h2 id="h2-u4E0Bu8F7Du4EA4u4E92u8FC7u7A0B"><a name="下载交互过程" class="reference-link"></a><span class="header-link octicon octicon-link"></span>下载交互过程</h2><ol>\n<li>client询问tracker下载文件的storage，参数为文件标识（卷名和文件名）；</li><li>tracker返回一台可用的storage；</li><li>client直接和storage通讯完成文件下载。<br>需要说明的是，client为使用FastDFS服务的调用方，client也应该是一台服务器，它对tracker和storage的调用均为服务器间的调用。</li></ol>\n', '2017-11-06 20:51:27', 19608, 3);
INSERT INTO `mine_articles` (`id`, `title`, `descs`, `url`, `content`, `htmlContent`, `updatetime`, `readingcount`, `type`) VALUES
(79, 'JAVA服务端通用数据导入导出组件V1.0', '工作中经常会遇到excel文件导入导出这类业务需求, 这些需求其实从根本上看都是对excel的解析处理,因此本人特地花了两天时间用JAVA写了一套通用的导入导出工具组件，为以后实现这类需求提高效率。组件源码地址：https://github.com/CodingGyd/project/tree/master/excel-utils', NULL, '# 前言\n&ensp;&ensp;&ensp;&ensp;工作中经常会遇到对excel这类文件进行导入导出的业务需求, 这些需求其实从根本上看都是对excel的解析处理,因此我特地花了两天时间写了一套通用的导入导出工具组件，为以后实现这类需求提高效率。当前版本V1.0，源码地址:https://github.com/CodingGyd/project/tree/master/excel-utils\n\n### 一、组件简介\n&ensp;&ensp;&ensp;&ensp; V1.0版实现了结合JAVA注解和反射思想进行excel的解析规则配置,两行代码即可完成对2007版和2003版excel文件的导入导出功能。\n&ensp;&ensp;&ensp;&ensp;本人致力于将该组件打造成一款通用的数据导入导出工具组件,追求在不改代码的前提下，进行极少的规则配置即可完成对excel、txt、dbf、pdf等各种格式数据文件的高效读取或生成。\n\n### 二、开发环境、技术框架、当前组件版本\n	JDK版本：1.7及以上\n	编译器：Eclipse\n	SpringBoot版本：1.3.3.RELEASE\n	POI版本：3.15\n	组件版本：V1.0\n\n### 三、组件框架主要类的uml图\n![UML类图](http://www.gydblog.com:8888/group1/M00/00/00/wKgACFojl-eAOxWfAAM2Uo9ioYw312.png "UML类图")\n\n### 四、使用示例\n\n1.定义excel记录行实体结构,并用注解进行配置说明。配置描述请参考源码地址的注释说明https://github.com/CodingGyd/project/tree/master/excel-utils\n\n```java\npackage com.codinggyd.excel.example;\n\nimport java.io.Serializable;\nimport java.util.Date;\n \nimport com.codinggyd.excel.annotation.ExcelFieldConfig;\nimport com.codinggyd.excel.annotation.ExcelSheetConfig;\nimport com.codinggyd.excel.annotation.ExcelFieldRule;\nimport com.codinggyd.excel.constant.ExcelConst;\nimport com.codinggyd.excel.constant.JavaFieldType;\n\n@ExcelSheetConfig(titleRowStartIndex=1,contentRowStartIndex=2,excelSuffix=ExcelConst.EXCEL_FORMAT_XLSX)\npublic class TestUser implements Serializable{\n\n	/**\n	 * \n	 */\n	private static final long serialVersionUID = -6106965608103174812L;\n\n	@ExcelFieldConfig(isPrimaryKey=true,name="姓名",index=0,javaType=JavaFieldType.TYPE_STRING, replaces = { @ExcelFieldRule(content = "上证", replace = "83"),@ExcelFieldRule(content = "深圳", replace = "90") })\n	private String name;\n	\n	@ExcelFieldConfig(name="年龄",index=1,javaType=JavaFieldType.TYPE_INTEGER)\n	private Integer age;\n	\n	@ExcelFieldConfig(name="工资",index=2,javaType=JavaFieldType.TYPE_DOUBLE)\n	private Double money;\n	\n	@ExcelFieldConfig(name="创建时间",index=3,javaType=JavaFieldType.TYPE_DATE)\n	private Date createTime;\n\n	public String getName() {\n		return name;\n	}\n\n	public void setName(String name) {\n		this.name = name;\n	}\n\n	public Integer getAge() {\n		return age;\n	}\n\n	public void setAge(Integer age) {\n		this.age = age;\n	}\n\n	public Double getMoney() {\n		return money;\n	}\n\n	public void setMoney(Double money) {\n		this.money = money;\n	}\n\n	public Date getCreateTime() {\n		return createTime;\n	}\n\n	public void setCreateTime(Date createTime) {\n		this.createTime = createTime;\n	}\n\n	@Override\n	public String toString() {\n		return "TestUser [name=" + name + ", age=" + age + ", money=" + money + ", createTime=" + createTime + "]";\n	}\n	\n}\n\n```\n2.读取2007版xlsxExcel示例，编写并运行测试单元。\n```java\npackage com.codinggyd.excel.example;\n\nimport java.io.File;\nimport java.io.FileInputStream;\nimport java.util.HashMap;\nimport java.util.List;\nimport java.util.Map;\nimport java.util.Map.Entry;\n\nimport com.codinggyd.excel.constant.ExcelConst;\nimport com.codinggyd.excel.core.ExcelParserUtils;\nimport com.codinggyd.excel.core.parsexcel.bean.ResultList;\nimport com.codinggyd.excel.core.parsexcel.inter.IExcelRowHandler;\n\nimport junit.framework.TestCase;\n \n/**\n * \n * <pre>\n * 类名:  TestExcelParser.java\n * 包名:  com.codinggyd.excel.example\n * 描述:  Excel解析方法测试\n * \n * 作者:  guoyd\n * 日期:  2017年12月3日\n *\n * Copyright @ 2017 Corpration Name\n * </pre>\n */\npublic class TestExcelParser extends TestCase  {\n	\n//	测试ExcelParserUtils#parse(InputStream is,Class<T> clazz,String format)\n	public void testParse1() throws Exception {\n		long start = System.currentTimeMillis();\n\n		String file = "G:/test.xlsx";\n		String format = ExcelConst.EXCEL_FORMAT_XLSX;\n		FileInputStream fis = new FileInputStream(new File(file));\n		ResultList<User> result = ExcelParserUtils.parse(fis, User.class, format);\n		System.out.println("错误报告:"+result.getMsg());\n		for (User user:result) {\n			System.out.println(user.toString());\n		}\n		System.out.println("解析数据量"+result.size()+"条,耗时"+(System.currentTimeMillis()-start)+"ms");\n\n	}\n}\n```\n运行结果截图：\n![读取2007版excel运行结果](http://www.gydblog.com:8888/group1/M00/00/00/wKgACFojmfOAZtzYAAB12NV_1ik018.png "读取2007版excel运行结果")\n\n3.导出2007版xlsxExcel示例，编写并运行测试单元。\n```java\npackage com.codinggyd.excel.example;\n\nimport java.io.File;\nimport java.io.FileOutputStream;\nimport java.util.ArrayList;\nimport java.util.Date;\nimport java.util.List;\n\nimport com.codinggyd.excel.constant.ExcelConst;\nimport com.codinggyd.excel.core.ExcelExporterUtils;\n\nimport junit.framework.TestCase;\n/**\n * \n * <pre>\n * 类名:  TestExcelExporter.java\n * 包名:  com.codinggyd.excel.example\n * 描述:  Excel生成方法测试\n * \n * 作者:  guoyd\n * 日期:  2017年12月3日\n *\n * Copyright @ 2017 Corpration Name\n * </pre>\n */\npublic class TestExcelExporter extends TestCase  {\n	\n//	测试ExcelExporterUtils#export(String format,Class<?> clazz,List<T> data,OutputStream outputStream) \n	public void testExporter2() throws Exception {\n		long start = System.currentTimeMillis();\n\n		String file = "G:/new2.xlsx";\n		FileOutputStream fos = new FileOutputStream(new File(file));\n		String format = ExcelConst.EXCEL_FORMAT_XLSX;\n		List<User> data = new ArrayList<User>();\n		for (int i=0;i<100000;i++) {\n			User t = new User();\n			t.setAge(i);\n			t.setName("测试"+i);\n			t.setMoney(1d*i);\n			t.setCreateTime(new Date());\n			data.add(t);\n		}\n		//一行代码调用生成\n		ExcelExporterUtils.export(format, User.class, data,fos); \n		 \n		System.out.println("导出数据量"+data.size()+"条,耗时"+(System.currentTimeMillis()-start)+"ms");\n\n	}\n	 \n}\n\n```\n运行结果截图\n![导出100万数据运行截图](http://www.gydblog.com:8888/group1/M00/00/00/wKgACFojnNSAL3MsAAAnfWVXjQs095.png "导出100万数据运行截图")\n![导出100万数据excel截图](http://www.gydblog.com:8888/group1/M00/00/00/wKgACFojnaGAJUp0AABS8kZWMq8639.png "导出100万数据excel截图")\n是不是很方便！ 简写到一行代码就搞定，读取时支持返回错误报告, 接口中还定义了其它方法,支持回调自定义解析格式。自行看源码注释吧。\n### 五、性能测试\n&ensp;&ensp;&ensp;&ensp;测试环境： 4GB内存、i5-3210M双核处理器、100万行的2007版excel。\n&ensp;&ensp;&ensp;&ensp;第1次,解析数据量1003472条,耗时30285ms\n&ensp;&ensp;&ensp;&ensp;第2次,解析数据量1003472条,耗时30750ms\n&ensp;&ensp;&ensp;&ensp;第3次,解析数据量1003472条,耗时25192ms\n&ensp;&ensp;&ensp;&ensp;第4次,解析数据量1003472条,耗时21411ms\n&ensp;&ensp;&ensp;&ensp;第5次,解析数据量1003472条,耗时25531ms\n&ensp;&ensp;&ensp;&ensp;第6次,解析数据量1003472条,耗时20963ms\n&ensp;&ensp;&ensp;&ensp;第7次,解析数据量1003472条,耗时20388ms\n&ensp;&ensp;&ensp;&ensp;第8次,解析数据量1003472条,耗时20026ms\n&ensp;&ensp;&ensp;&ensp;第9次,解析数据量1003472条,耗时19644ms\n&ensp;&ensp;&ensp;&ensp;第10次,解析数据量1003472条,耗时21206ms\n\n', '<h1 id="h1-u524Du8A00"><a name="前言" class="reference-link"></a><span class="header-link octicon octicon-link"></span>前言</h1><p>&ensp;&ensp;&ensp;&ensp;工作中经常会遇到对excel这类文件进行导入导出的业务需求, 这些需求其实从根本上看都是对excel的解析处理,因此我特地花了两天时间写了一套通用的导入导出工具组件，为以后实现这类需求提高效率。当前版本V1.0，源码地址:<a href="https://github.com/CodingGyd/project/tree/master/excel-utils">https://github.com/CodingGyd/project/tree/master/excel-utils</a></p>\n<h3 id="h3--"><a name="一、组件简介" class="reference-link"></a><span class="header-link octicon octicon-link"></span>一、组件简介</h3><p>&ensp;&ensp;&ensp;&ensp; V1.0版实现了结合JAVA注解和反射思想进行excel的解析规则配置,两行代码即可完成对2007版和2003版excel文件的导入导出功能。<br>&ensp;&ensp;&ensp;&ensp;本人致力于将该组件打造成一款通用的数据导入导出工具组件,追求在不改代码的前提下，进行极少的规则配置即可完成对excel、txt、dbf、pdf等各种格式数据文件的高效读取或生成。</p>\n<h3 id="h3--"><a name="二、开发环境、技术框架、当前组件版本" class="reference-link"></a><span class="header-link octicon octicon-link"></span>二、开发环境、技术框架、当前组件版本</h3><pre><code>JDK版本：1.7及以上\n编译器：Eclipse\nSpringBoot版本：1.3.3.RELEASE\nPOI版本：3.15\n组件版本：V1.0\n</code></pre><h3 id="h3--uml-"><a name="三、组件框架主要类的uml图" class="reference-link"></a><span class="header-link octicon octicon-link"></span>三、组件框架主要类的uml图</h3><p><img src="http://www.gydblog.com:8888/group1/M00/00/00/wKgACFojl-eAOxWfAAM2Uo9ioYw312.png" alt="UML类图" title="UML类图"></p>\n<h3 id="h3--"><a name="四、使用示例" class="reference-link"></a><span class="header-link octicon octicon-link"></span>四、使用示例</h3><p>1.定义excel记录行实体结构,并用注解进行配置说明。配置描述请参考源码地址的注释说明<a href="https://github.com/CodingGyd/project/tree/master/excel-utils">https://github.com/CodingGyd/project/tree/master/excel-utils</a></p>\n<pre><code class="lang-java">package com.codinggyd.excel.example;\n\nimport java.io.Serializable;\nimport java.util.Date;\n\nimport com.codinggyd.excel.annotation.ExcelFieldConfig;\nimport com.codinggyd.excel.annotation.ExcelSheetConfig;\nimport com.codinggyd.excel.annotation.ExcelFieldRule;\nimport com.codinggyd.excel.constant.ExcelConst;\nimport com.codinggyd.excel.constant.JavaFieldType;\n\n@ExcelSheetConfig(titleRowStartIndex=1,contentRowStartIndex=2,excelSuffix=ExcelConst.EXCEL_FORMAT_XLSX)\npublic class TestUser implements Serializable{\n\n    /**\n     * \n     */\n    private static final long serialVersionUID = -6106965608103174812L;\n\n    @ExcelFieldConfig(isPrimaryKey=true,name=&quot;姓名&quot;,index=0,javaType=JavaFieldType.TYPE_STRING, replaces = { @ExcelFieldRule(content = &quot;上证&quot;, replace = &quot;83&quot;),@ExcelFieldRule(content = &quot;深圳&quot;, replace = &quot;90&quot;) })\n    private String name;\n\n    @ExcelFieldConfig(name=&quot;年龄&quot;,index=1,javaType=JavaFieldType.TYPE_INTEGER)\n    private Integer age;\n\n    @ExcelFieldConfig(name=&quot;工资&quot;,index=2,javaType=JavaFieldType.TYPE_DOUBLE)\n    private Double money;\n\n    @ExcelFieldConfig(name=&quot;创建时间&quot;,index=3,javaType=JavaFieldType.TYPE_DATE)\n    private Date createTime;\n\n    public String getName() {\n        return name;\n    }\n\n    public void setName(String name) {\n        this.name = name;\n    }\n\n    public Integer getAge() {\n        return age;\n    }\n\n    public void setAge(Integer age) {\n        this.age = age;\n    }\n\n    public Double getMoney() {\n        return money;\n    }\n\n    public void setMoney(Double money) {\n        this.money = money;\n    }\n\n    public Date getCreateTime() {\n        return createTime;\n    }\n\n    public void setCreateTime(Date createTime) {\n        this.createTime = createTime;\n    }\n\n    @Override\n    public String toString() {\n        return &quot;TestUser [name=&quot; + name + &quot;, age=&quot; + age + &quot;, money=&quot; + money + &quot;, createTime=&quot; + createTime + &quot;]&quot;;\n    }\n\n}\n</code></pre>\n<p>2.读取2007版xlsxExcel示例，编写并运行测试单元。</p>\n<pre><code class="lang-java">package com.codinggyd.excel.example;\n\nimport java.io.File;\nimport java.io.FileInputStream;\nimport java.util.HashMap;\nimport java.util.List;\nimport java.util.Map;\nimport java.util.Map.Entry;\n\nimport com.codinggyd.excel.constant.ExcelConst;\nimport com.codinggyd.excel.core.ExcelParserUtils;\nimport com.codinggyd.excel.core.parsexcel.bean.ResultList;\nimport com.codinggyd.excel.core.parsexcel.inter.IExcelRowHandler;\n\nimport junit.framework.TestCase;\n\n/**\n * \n * &lt;pre&gt;\n * 类名:  TestExcelParser.java\n * 包名:  com.codinggyd.excel.example\n * 描述:  Excel解析方法测试\n * \n * 作者:  guoyd\n * 日期:  2017年12月3日\n *\n * Copyright @ 2017 Corpration Name\n * &lt;/pre&gt;\n */\npublic class TestExcelParser extends TestCase  {\n\n//    测试ExcelParserUtils#parse(InputStream is,Class&lt;T&gt; clazz,String format)\n    public void testParse1() throws Exception {\n        long start = System.currentTimeMillis();\n\n        String file = &quot;G:/test.xlsx&quot;;\n        String format = ExcelConst.EXCEL_FORMAT_XLSX;\n        FileInputStream fis = new FileInputStream(new File(file));\n        ResultList&lt;User&gt; result = ExcelParserUtils.parse(fis, User.class, format);\n        System.out.println(&quot;错误报告:&quot;+result.getMsg());\n        for (User user:result) {\n            System.out.println(user.toString());\n        }\n        System.out.println(&quot;解析数据量&quot;+result.size()+&quot;条,耗时&quot;+(System.currentTimeMillis()-start)+&quot;ms&quot;);\n\n    }\n}\n</code></pre>\n<p>运行结果截图：<br><img src="http://www.gydblog.com:8888/group1/M00/00/00/wKgACFojmfOAZtzYAAB12NV_1ik018.png" alt="读取2007版excel运行结果" title="读取2007版excel运行结果"></p>\n<p>3.导出2007版xlsxExcel示例，编写并运行测试单元。</p>\n<pre><code class="lang-java">package com.codinggyd.excel.example;\n\nimport java.io.File;\nimport java.io.FileOutputStream;\nimport java.util.ArrayList;\nimport java.util.Date;\nimport java.util.List;\n\nimport com.codinggyd.excel.constant.ExcelConst;\nimport com.codinggyd.excel.core.ExcelExporterUtils;\n\nimport junit.framework.TestCase;\n/**\n * \n * &lt;pre&gt;\n * 类名:  TestExcelExporter.java\n * 包名:  com.codinggyd.excel.example\n * 描述:  Excel生成方法测试\n * \n * 作者:  guoyd\n * 日期:  2017年12月3日\n *\n * Copyright @ 2017 Corpration Name\n * &lt;/pre&gt;\n */\npublic class TestExcelExporter extends TestCase  {\n\n//    测试ExcelExporterUtils#export(String format,Class&lt;?&gt; clazz,List&lt;T&gt; data,OutputStream outputStream) \n    public void testExporter2() throws Exception {\n        long start = System.currentTimeMillis();\n\n        String file = &quot;G:/new2.xlsx&quot;;\n        FileOutputStream fos = new FileOutputStream(new File(file));\n        String format = ExcelConst.EXCEL_FORMAT_XLSX;\n        List&lt;User&gt; data = new ArrayList&lt;User&gt;();\n        for (int i=0;i&lt;100000;i++) {\n            User t = new User();\n            t.setAge(i);\n            t.setName(&quot;测试&quot;+i);\n            t.setMoney(1d*i);\n            t.setCreateTime(new Date());\n            data.add(t);\n        }\n        //一行代码调用生成\n        ExcelExporterUtils.export(format, User.class, data,fos); \n\n        System.out.println(&quot;导出数据量&quot;+data.size()+&quot;条,耗时&quot;+(System.currentTimeMillis()-start)+&quot;ms&quot;);\n\n    }\n\n}\n</code></pre>\n<p>运行结果截图<br><img src="http://www.gydblog.com:8888/group1/M00/00/00/wKgACFojnNSAL3MsAAAnfWVXjQs095.png" alt="导出100万数据运行截图" title="导出100万数据运行截图"><br><img src="http://www.gydblog.com:8888/group1/M00/00/00/wKgACFojnaGAJUp0AABS8kZWMq8639.png" alt="导出100万数据excel截图" title="导出100万数据excel截图"><br>是不是很方便！ 简写到一行代码就搞定，读取时支持返回错误报告, 接口中还定义了其它方法,支持回调自定义解析格式。自行看源码注释吧。</p>\n<h3 id="h3--"><a name="五、性能测试" class="reference-link"></a><span class="header-link octicon octicon-link"></span>五、性能测试</h3><p>&ensp;&ensp;&ensp;&ensp;测试环境： 4GB内存、i5-3210M双核处理器、100万行的2007版excel。<br>&ensp;&ensp;&ensp;&ensp;第1次,解析数据量1003472条,耗时30285ms<br>&ensp;&ensp;&ensp;&ensp;第2次,解析数据量1003472条,耗时30750ms<br>&ensp;&ensp;&ensp;&ensp;第3次,解析数据量1003472条,耗时25192ms<br>&ensp;&ensp;&ensp;&ensp;第4次,解析数据量1003472条,耗时21411ms<br>&ensp;&ensp;&ensp;&ensp;第5次,解析数据量1003472条,耗时25531ms<br>&ensp;&ensp;&ensp;&ensp;第6次,解析数据量1003472条,耗时20963ms<br>&ensp;&ensp;&ensp;&ensp;第7次,解析数据量1003472条,耗时20388ms<br>&ensp;&ensp;&ensp;&ensp;第8次,解析数据量1003472条,耗时20026ms<br>&ensp;&ensp;&ensp;&ensp;第9次,解析数据量1003472条,耗时19644ms<br>&ensp;&ensp;&ensp;&ensp;第10次,解析数据量1003472条,耗时21206ms</p>\n', '2017-12-03 14:47:34', 17674, 3);

-- --------------------------------------------------------

--
-- 表的结构 `mine_article_keyword_relation`
--

CREATE TABLE IF NOT EXISTS `mine_article_keyword_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `articleId` int(11) NOT NULL,
  `keyId` int(11) NOT NULL,
  `updatetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=73 ;

--
-- 转存表中的数据 `mine_article_keyword_relation`
--

INSERT INTO `mine_article_keyword_relation` (`id`, `articleId`, `keyId`, `updatetime`) VALUES
(1, 1, 4, '2017-11-05 17:05:14'),
(2, 1, 5, '2017-11-05 17:05:14'),
(3, 1, 7, '2017-11-05 17:05:14'),
(4, 75, 3, '2017-11-05 17:39:27'),
(5, 75, 6, '2017-11-05 17:39:27'),
(6, 75, 7, '2017-11-05 17:39:27'),
(8, 76, 1, '2017-11-05 18:01:21'),
(9, 76, 3, '2017-11-05 18:01:21'),
(10, 76, 5, '2017-11-05 18:01:21'),
(11, 77, 1, '2017-11-05 18:02:31'),
(12, 77, 3, '2017-11-05 18:02:31'),
(13, 77, 4, '2017-11-05 18:02:31'),
(14, 77, 5, '2017-11-05 18:02:31'),
(15, 77, 6, '2017-11-05 18:02:31'),
(16, 77, 7, '2017-11-05 18:02:31'),
(19, 78, 8, '2017-11-06 20:51:27'),
(20, 78, 9, '2017-11-06 20:51:27'),
(69, 79, 10, '2017-12-03 14:47:35'),
(70, 79, 11, '2017-12-03 14:47:35'),
(71, 79, 12, '2017-12-03 14:47:35'),
(72, 79, 4, '2017-12-03 14:47:35');

-- --------------------------------------------------------

--
-- 表的结构 `mine_dailyessays`
--

CREATE TABLE IF NOT EXISTS `mine_dailyessays` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(500) NOT NULL,
  `updatetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `tmp` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='随笔' AUTO_INCREMENT=7 ;

--
-- 转存表中的数据 `mine_dailyessays`
--

INSERT INTO `mine_dailyessays` (`id`, `content`, `updatetime`, `tmp`) VALUES
(1, '青春，一半明媚，一半忧伤。 它是一本惊天地泣鬼神的着作，而我们却读的太匆忙。 于不经意间，青春的书籍悄然合上，以至于我们要重新研读它时， 却发现青春的字迹早已落满尘埃，模糊不清。', '2017-11-02 23:11:00', ''),
(5, '使用ztree 为啥我整这么复杂。。。。', '2017-11-08 11:28:24', NULL),
(6, '严谨，严谨，严谨.', '2017-11-13 18:27:40', NULL);

-- --------------------------------------------------------

--
-- 表的结构 `mine_keywords`
--

CREATE TABLE IF NOT EXISTS `mine_keywords` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `updatetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='关键词表' AUTO_INCREMENT=13 ;

--
-- 转存表中的数据 `mine_keywords`
--

INSERT INTO `mine_keywords` (`id`, `name`, `updatetime`) VALUES
(1, 'fastdfs', '2017-11-05 12:26:41'),
(3, 'redis', '2017-11-05 13:56:12'),
(4, 'springboot', '2017-11-05 13:56:25'),
(5, 'java', '2017-11-05 14:32:06'),
(6, 'php', '2017-11-05 14:32:15'),
(7, 'android', '2017-11-05 14:32:20'),
(8, '分布式文件系统', '2017-11-06 20:40:23'),
(9, '文件服务器', '2017-11-06 20:40:29'),
(10, 'excel', '2017-12-02 16:48:44'),
(11, 'poi', '2017-12-02 16:48:49'),
(12, '数据导入导出', '2017-12-02 16:48:59');

-- --------------------------------------------------------

--
-- 表的结构 `mine_sysconst`
--

CREATE TABLE IF NOT EXISTS `mine_sysconst` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lb` varchar(20) DEFAULT NULL COMMENT '常量类别代码',
  `lbmc` varchar(50) DEFAULT NULL COMMENT '常量类别名称',
  `dm` varchar(20) DEFAULT NULL COMMENT '常量代码',
  `ms` varchar(50) DEFAULT NULL COMMENT '常量名称',
    `remarks` varchar(50) DEFAULT NULL COMMENT '注释',

  `updatetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='系统常量表' AUTO_INCREMENT=4 ;

--
-- 转存表中的数据 `mine_sysconst`
--

INSERT INTO `mine_sysconst` (`id`, `lb`, `lbmc`, `dm`, `ms`, `remarks`,`updatetime`) VALUES
(1, '100', '文章分类', '1', '资料网站', '资料网站','2017-09-22 23:48:20'),
(2, '100', '文章分类', '2', '生活文摘', '生活文摘','2017-09-22 23:48:20'),
(3, '100', '文章分类', '3', '技术笔记', '技术笔记','2017-09-22 23:48:20');

-- --------------------------------------------------------

--
-- 表的结构 `mine_utilfunction`
--

CREATE TABLE IF NOT EXISTS `mine_utilfunction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `content` text NOT NULL,
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `readingcount` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='mine工程-功能函数模块-功能函数信息表' AUTO_INCREMENT=14 ;

--
-- 转存表中的数据 `mine_utilfunction`
--

INSERT INTO `mine_utilfunction` (`id`, `title`, `content`, `updatetime`, `readingcount`) VALUES
(1, '计算输入日期区间包含2月29日的次数', '  private  boolean hasRun(Date date1, Date date2) {\n\n      SimpleDateFormatformat= newSimpleDateFormat("yyyy-MM-dd hh:mm:ss");\n\n      Log.debug(logger, format.format(date1));\n\n      Log.debug(logger, format.format(date2));\n\n      Calendarcalendar1= Calendar.getInstance();\n\n      Calendarcalendar2= Calendar.getInstance();\n\n      if(date1.getTime() >date2.getTime()){\n\n         calendar1.setTime(date2);\n\n         calendar2.setTime(date1);\n\n      }else{\n\n         calendar1.setTime(date1);\n\n         calendar2.setTime(date2);\n\n      }\n\n      int yyyy1 = calendar1.get(Calendar.YEAR);\n\n      int yyyy2 = calendar2.get(Calendar.YEAR);\n\n      if (yyyy1 == yyyy2 && leapYear(yyyy1)) {\n\n         Calendarcalendar4= Calendar.getInstance();\n\n         calendar4.set(yyyy1, 1, 29);// 月份是从0开始的\n\n         if (!calendar4.before(calendar1) && !calendar4.after(calendar2)) {\n\n            return true;\n\n         }\n\n      }elseif(yyyy1< yyyy2){\n\n         int containsCount = 0;\n\n         yyyy1 = yyyy1 +1;//区间端点不考虑，下面额外处理\n\n         while (yyyy1 < yyyy2) {\n\n            if(leapYear(yyyy1)){\n\n                containsCount++;\n\n            }\n\n            yyyy1++;\n\n         }\n\n         // 日期区间的两个端点的日期额外处理-起始点\n\n         Calendarcalendar4= Calendar.getInstance();\n\n         if(leapYear(calendar1.get(Calendar.YEAR))){\n\n            calendar4.set(calendar1.get(Calendar.YEAR), 1, 29);// 月份是从0开始的\n\n            if (!calendar4.before(calendar1) && !calendar4.after(calendar2)) {\n\n                containsCount++;\n\n            }\n\n         }\n\n         // 日期区间的两个端点的日期额外处理-截止点\n\n         if(leapYear(calendar2.get(Calendar.YEAR))){\n\n            calendar4.set(calendar2.get(Calendar.YEAR), 1, 29);// 月份是从0开始的\n\n            if (!calendar4.before(calendar1) && !calendar4.after(calendar2)) {\n\n                containsCount++;\n\n            }\n\n         }\n\n         Log.debug(logger, "日期区间包含2月29日的次数:"+containsCount);\n\n         if (containsCount > 0) {\n\n            return true;\n\n         }\n\n      }\n\n      return false;\n\n   }', '2017-01-21 10:03:58', 0),
(7, 'sqlserver 递归查询', '    WITH CTE AS\r\n    	   (\r\n    		SELECT ID,CID ,MC,FID,0 AS LVL FROM usiCPMX WHERE CID = -1\r\n		    		 UNION ALL\r\n		    SELECT D.ID, D.CID,D.MC,D.FID,LVL+1 FROM CTE C INNER JOIN usiCPMX D\r\n		     ON C.CID = D.FID\r\n 		   )\r\n 		   SELECT ID uuid, CID id,MC name,FID pid  FROM CTE WHERE 1=1 AND LVL <=1\r\n	     ', '2017-04-17 04:09:37', 0),
(8, 'java流操作踩坑记录', '用springboot的文件上传功能，将上传的文件流对象由controller层传入service层，一直报错java.ioexception:stream closed,原因是流被关闭了，如果需要重用流，可以多拷贝几份，拷贝代码：\r\nByteArrayOutputStream baos = new ByteArrayOutputStream();\r\n\r\n// Fake code simulating the copy\r\n// You can generally do better with nio if you need...\r\n// And please, unlike me, do something about the Exceptions :D\r\nbyte[] buffer = new byte[1024];\r\nint len;\r\nwhile ((len = input.read(buffer)) > -1 ) {\r\n    baos.write(buffer, 0, len);\r\n}\r\nbaos.flush();\r\n\r\n// Open new InputStreams using the recorded bytes\r\n// Can be repeated as many times as you wish\r\nInputStream is1 = new ByteArrayInputStream(baos.toByteArray()); \r\nInputStream is2 = new ByteArrayInputStream(baos.toByteArray()); ', '2017-05-17 07:58:38', 0),
(9, 'mybatis报错', '错误：org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.exceptions.PersistenceException: \r\n### Error querying database.  Cause: java.lang.UnsupportedOperationException\r\n### The error may exist in file [D:\\developer\\companycode2\\workspace-mainbranch\\sys\\faiscms-new\\target\\classes\\com\\hundsun\\gildata\\mybatis-cs-gilUser.xml]\r\n### The error may involve com.hundsun.gildata.faiscms.mapper.GilUserMapper.queryBatchOperatorCode2\r\n### The error occurred while handling results\r\n### SQL: SELECT distinct OperatorCode FROM SysUserRelation where OperatorCode   IN   (         ?    ,     ?    ,     ?    ,     ?       )\r\n### Cause: java.lang.UnsupportedOperationException\r\n\r\n原因：mybatisxml文件里resultType节点应该指明为list里数据的类型而不是list类型本身', '2017-05-17 08:16:13', 0),
(10, 'Maven+spring项目在部署到tomcat时一直报错找不到类ContextLoaderListener ', 'Maven+spring项目在部署到tomcat时一直报错找不到类\r\n严重: Error configuring application listener of class org.springframework.web.context.ContextLoaderListener \r\nJava.lang.ClassNotFoundException: org.springframework.web.context.ContextLoaderListener \r\nat org.apache.catalina.loader.WebappClassLoader.loadClass(WebappClassLoader.java:1352) \r\nat org.apache.catalina.loader.WebappClassLoader.loadClass(WebappClassLoader.java:1198) \r\nat org.apache.catalina.core.StandardContext.listenerStart(StandardContext.java:3677) \r\nat org.apache.catalina.core.StandardContext.start(StandardContext.java:4187) \r\nat org.apache.catalina.core.ContainerBase.start(ContainerBase.java:1013) \r\nat org.apache.catalina.core.StandardHost.start(StandardHost.java:718) \r\nat org.apache.catalina.core.ContainerBase.start(ContainerBase.java:1013) \r\nat org.apache.catalina.core.StandardEngine.start(StandardEngine.java:442) \r\nat org.apache.catalina.core.StandardService.start(StandardService.java:450) \r\nat org.apache.catalina.core.StandardServer.start(StandardServer.java:709) \r\nat org.apache.catalina.startup.Catalina.start(Catalina.java:551) \r\nat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) \r\nat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39) \r\nat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25) \r\nat java.lang.reflect.Method.invoke(Method.java:585) \r\nat org.apache.catalina.startup.Bootstrap.start(Bootstrap.java:294) \r\nat org.apache.catalina.startup.Bootstrap.main(Bootstrap.java:432)\r\n\r\n\r\n百度了一波：解决方法：\r\n其实可能是你的jar文件没有同步发布到自己项目的lib目录中(如果你是用Maven进行构建的话) 可以试试 下面的办法 项目点击右键 点击 Properties 选择Deployment Assembly 再点击右边的Add按钮 选择Java Build Path Entries后点击Next按钮 然后选择你的Maven Dependencies 确定即可', '2017-06-11 14:10:03', 0),
(11, 'Linux服务器安装tomcat并启动后,在不同机器上访问管理界面时候出现403错误', '\nLinux 下装好tomcat并启动后,在不同机器上访问管理界面时候出现403错误, 查了很多资料,改了conf/tomcat-user.xml 下的用户角色 ，然后去tomcat/webapp/manager/META-INF/context.xml下修改了context加上不同机器的IP都没用，最后把context节点下的Value节点注释掉就好了。', '2017-06-11 14:11:58', 0),
(12, 'java后台发送highchart的json图表模板数据到phantomJs生成图表', '\n压力测试调用phantomJs服务生成highchart图表出现如下Permission denied错误\njava.lang.RuntimeException: java.net.SocketException: Permission  mission denied: connect，原因未明，可能是压力测试频繁访问导致http的限制错误，连续访问了3600次后出现了Permission denied', '2017-06-11 14:14:41', 0),
(13, '解决java.lang.OutOfMemoryError: GC overhead limit exceeded和Java heap space', '     背景：最近在公司接到一个开发任务，是要求在后台定时每个月生成基金的投资报告pdf文件，pdf中包含了大量的图表。\n     经过调研，决定采用DynamicReport+Highcharts+PhantomJs技术来实现该需求。\n     DynamicReport： 纯后台生成报表框架，简单，快速。支持生成一些简单的图表并保存为pdf文件。\n     HighCharts: 流行的前端图表框架，支持大量酷炫的图表样式。\n     phantomJs：一个基于WebKit内核的服务端JavaScript引擎，全面支持web并不需要任何浏览器，由于我接到的开发任务是纯后台生成各种图表，没有前端，\n而DynamicReport实现的图表比较丑陋，因此采用了通过PhantomJs调用Highcharts的js模板来在内存中生成各种酷炫的图，即在后台发送highcharts图表json数据到phantomJs，\n然后获取生成的图表数据Base64字符串，然后生成图片对象并通过DynamicReport保存到pdf文件里。\n     好了，啪啪啪几天后完成了需求目标，然后在进行压力测试时发现一个大bug(⊙o⊙)，我在代码里循环连续生成3000份pdf文件，发现每次生成到265份后程序就出现内存溢出错误\nGC overhead limit exceeded和Java heap space，这是为什么呢？我写的代码里各个对象在完成它使命后都主动通知GC进行垃圾回收了呀！无奈，通过jdk自带的\n一个内存检测分析工具jvisualvm进行内存分析，发现程序开始运行后内存只升不降，然后飙到2G多，超出了我的程序的堆内存的默认顶峰，最终导致内存的溢出错误发生。\n我擦，java不是自带了垃圾回收机制吗？，怎么内存一直往上飙呢？我的代码没毛病呀!,最后换eclipse的插件Memory Analysis内存分析工具再次进行跟踪，发现是用的DynamicReport\n框架中有一个线程Map对象一直无法被释放，并且占用内存越来越多，它就是元凶!原来是我每次调用DynamicReport框架的pdf生成类生成pdf时，DynamicReport\n都会往这个线程Map里生成一个线程，并且pdf生成后也一直被占用着，导致GC无法对其进行释放，坑货！\n     最终解决方案：将调用DynamicReport生成pdf的代码放入手动创建的子线程中执行，每次子线程执行完后，主动释放该子线程，那么该子线程中所有对象都会被释放啦，包括dynamicreport的一些对象哦。\n伪代码如下：\n	for (int i=0;i>size;i++) {\n		//CountDownLatch 作用：让某一个线程等待其他线程执行完后再执行，在这里是让主线程等待执行创建pdf任务的单个子线程执行完后再进入下一个循环，\n		//CountDownLatch是通过一个计数器来实现的，计数器的初始值为线程的数量。每当一个线程完成了自己的任务后，计数器的值就会减1。当计数器值到达0时，它表示所有的线程已经完成了任务，然后在闭锁上等待的线程就可以恢复执行任务。\n        	CountDownLatch countDownLatch = new CountDownLatch(1);\n		Runnable task = new MyRunnable(fund,startDate,endDate,countDownLatch);\n		Thread t = new Thread(task);\n		t.start();\n		countDownLatch.await();//主线程等待子线程执行完后进入下一个循环\n		t.interrupt();//子线程执行完后手动释放资源\n	}\n\n	private class MyRunnable implements Runnable {\n		CountDownLatch countDownLatch ;\n		MyRunnable (CountDownLatch  countDownLatch ) {\n			this.countDownLatch  = countDownLatch ;\n		}\n		run () {\n			//调用DynamicReport框架+HighCharts+PhantomJs执行pdf生成任务\n			///.......略............\n			//...................\n				\n			this.countDownLatch.countDown();//任务完成后，计数器数量减1  \n\n		}\n	}', '2017-06-11 15:07:50', 0);

-- --------------------------------------------------------

--
-- 表的结构 `mr_records`
--

CREATE TABLE IF NOT EXISTS `mr_records` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `price` double NOT NULL,
  `name` varchar(255) NOT NULL,
  `remark` text,
  `spendtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=23 ;

--
-- 转存表中的数据 `mr_records`
--

INSERT INTO `mr_records` (`id`, `price`, `name`, `remark`, `spendtime`) VALUES
(20, 800, '给二姐付来回车费', '支出', '2017-04-01 22:59:59'),
(21, 50, '烧烤', '支出', '2017-04-01 23:00:31'),
(22, 52, '买菜', '支出', '2017-04-03 09:46:03');

-- --------------------------------------------------------

--
-- 表的结构 `mr_userinfo`
--

CREATE TABLE IF NOT EXISTS `mr_userinfo` (
  `name` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `address` varchar(255) NOT NULL,
  `tel` varchar(255) NOT NULL,
  `regdate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`tel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日常开销登记系统用户表';

--
-- 转存表中的数据 `mr_userinfo`
--

INSERT INTO `mr_userinfo` (`name`, `password`, `address`, `tel`, `regdate`) VALUES
('gyd', '123', '123', '15974154924', '2016-12-25 20:36:43');

-- --------------------------------------------------------

--
-- 表的结构 `sourceinfo`
--

CREATE TABLE IF NOT EXISTS `sourceinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `type` int(11) NOT NULL COMMENT '文档1 网址2',
  `location` varchar(255) DEFAULT NULL,
  `time` varchar(255) NOT NULL,
  `url` text NOT NULL,
  `userId` int(11) NOT NULL,
  `remark` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=41 ;

-- --------------------------------------------------------

--
-- 表的结构 `t_attach`
--

CREATE TABLE IF NOT EXISTS `t_attach` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `fname` varchar(100) NOT NULL DEFAULT '',
  `ftype` varchar(50) DEFAULT '',
  `fkey` varchar(100) NOT NULL DEFAULT '',
  `author_id` int(10) DEFAULT NULL,
  `created` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `t_comments`
--

CREATE TABLE IF NOT EXISTS `t_comments` (
  `coid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `cid` int(10) unsigned DEFAULT '0',
  `created` int(10) unsigned DEFAULT '0',
  `author` varchar(200) DEFAULT NULL,
  `author_id` int(10) unsigned DEFAULT '0',
  `owner_id` int(10) unsigned DEFAULT '0',
  `mail` varchar(200) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `ip` varchar(64) DEFAULT NULL,
  `agent` varchar(200) DEFAULT NULL,
  `content` text,
  `type` varchar(16) DEFAULT 'comment',
  `status` varchar(16) DEFAULT 'approved',
  `parent` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`coid`),
  KEY `cid` (`cid`),
  KEY `created` (`created`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `t_contents`
--

CREATE TABLE IF NOT EXISTS `t_contents` (
  `cid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT NULL,
  `slug` varchar(200) DEFAULT NULL,
  `created` int(10) unsigned DEFAULT '0',
  `modified` int(10) unsigned DEFAULT '0',
  `content` text COMMENT '内容文字',
  `author_id` int(10) unsigned DEFAULT '0',
  `type` varchar(16) DEFAULT 'post',
  `status` varchar(16) DEFAULT 'publish',
  `tags` varchar(200) DEFAULT NULL,
  `categories` varchar(200) DEFAULT NULL,
  `hits` int(10) unsigned DEFAULT '0',
  `comments_num` int(10) unsigned DEFAULT '0',
  `allow_comment` tinyint(1) DEFAULT '1',
  `allow_ping` tinyint(1) DEFAULT '1',
  `allow_feed` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`cid`),
  UNIQUE KEY `slug` (`slug`),
  KEY `created` (`created`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- 转存表中的数据 `t_contents`
--

INSERT INTO `t_contents` (`cid`, `title`, `slug`, `created`, `modified`, `content`, `author_id`, `type`, `status`, `tags`, `categories`, `hits`, `comments_num`, `allow_comment`, `allow_ping`, `allow_feed`) VALUES
(1, 'about my blog', 'about', 1487853610, 1487872488, '### Hello World\r\n\r\nabout me\r\n\r\n### ...\r\n\r\n...', 1, 'page', 'publish', NULL, NULL, 11946, 0, 1, 1, 1),
(2, 'Hello My Blog', NULL, 1487861184, 1487872798, '## Hello  World.\r\n\r\n> ...\r\n\r\n----------\r\n\r\n\r\n<!--more-->\r\n\r\n```java\r\npublic static void main(String[] args){\r\n    System.out.println("Hello 13 Blog.");\r\n}\r\n```', 1, 'post', 'publish', '', 'default', 23128, 0, 1, 1, 1);

-- --------------------------------------------------------

--
-- 表的结构 `t_logs`
--

CREATE TABLE IF NOT EXISTS `t_logs` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `action` varchar(100) DEFAULT NULL,
  `data` varchar(2000) DEFAULT NULL,
  `author_id` int(10) DEFAULT NULL,
  `ip` varchar(20) DEFAULT NULL,
  `created` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- 转存表中的数据 `t_logs`
--

INSERT INTO `t_logs` (`id`, `action`, `data`, `author_id`, `ip`, `created`) VALUES
(1, '登录后台', NULL, 1, '127.0.0.1', 1524126268),
(2, '登录后台', NULL, 1, '0:0:0:0:0:0:0:1', 1524127703),
(3, '登录后台', NULL, 1, '0:0:0:0:0:0:0:1', 1524211276),
(4, '登录后台', NULL, 1, '0:0:0:0:0:0:0:1', 1524215039);

-- --------------------------------------------------------

--
-- 表的结构 `t_metas`
--

CREATE TABLE IF NOT EXISTS `t_metas` (
  `mid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `slug` varchar(200) DEFAULT NULL,
  `type` varchar(32) NOT NULL DEFAULT '',
  `description` varchar(200) DEFAULT NULL,
  `sort` int(10) unsigned DEFAULT '0',
  `parent` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`mid`),
  KEY `slug` (`slug`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- 转存表中的数据 `t_metas`
--

INSERT INTO `t_metas` (`mid`, `name`, `slug`, `type`, `description`, `sort`, `parent`) VALUES
(1, 'default', NULL, 'category', NULL, 0, 0),
(6, 'my github', 'https://github.com/CodingGyd', 'link', NULL, 0, 0);

-- --------------------------------------------------------

--
-- 表的结构 `t_options`
--

CREATE TABLE IF NOT EXISTS `t_options` (
  `name` varchar(32) NOT NULL DEFAULT '',
  `value` varchar(1000) DEFAULT '',
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `t_options`
--

INSERT INTO `t_options` (`name`, `value`, `description`) VALUES
('site_description', '顺顺郭的博客', NULL),
('site_keywords', '顺顺郭的博客', NULL),
('site_record', '湘ICP备17020097号-1', '备案号'),
('site_theme', 'default', NULL),
('site_title', 'My Blog', ''),
('social_github', '', NULL),
('social_twitter', '', NULL),
('social_weibo', '', NULL),
('social_zhihu', '', NULL);

-- --------------------------------------------------------

--
-- 表的结构 `t_relationships`
--

CREATE TABLE IF NOT EXISTS `t_relationships` (
  `cid` int(10) unsigned NOT NULL,
  `mid` int(10) unsigned NOT NULL,
  PRIMARY KEY (`cid`,`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `t_relationships`
--

INSERT INTO `t_relationships` (`cid`, `mid`) VALUES
(2, 1);

-- --------------------------------------------------------

--
-- 表的结构 `t_users`
--

CREATE TABLE IF NOT EXISTS `t_users` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(32) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `home_url` varchar(200) DEFAULT NULL,
  `screen_name` varchar(32) DEFAULT NULL,
  `created` int(10) unsigned DEFAULT '0',
  `activated` int(10) unsigned DEFAULT '0',
  `logged` int(10) unsigned DEFAULT '0',
  `group_name` varchar(16) DEFAULT 'visitor',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `name` (`username`),
  UNIQUE KEY `mail` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- 转存表中的数据 `t_users`
--

INSERT INTO `t_users` (`uid`, `username`, `password`, `email`, `home_url`, `screen_name`, `created`, `activated`, `logged`, `group_name`) VALUES
(1, 'admin', 'a66abb5684c45962d887564f08346e8d', '1034683568@qq.com', NULL, 'admin', 1490756162, 0, 0, 'visitor');

-- --------------------------------------------------------

--
-- 表的结构 `userinfo`
--

CREATE TABLE IF NOT EXISTS `userinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(255) NOT NULL,
  `registerTime` varchar(255) NOT NULL,
  `recentLoginTime` varchar(255) NOT NULL,
  `loginCount` int(11) NOT NULL DEFAULT '0',
  `sex` char(4) NOT NULL,
  `icon` text,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10 ;

--
-- 转存表中的数据 `userinfo`
--

INSERT INTO `userinfo` (`id`, `phone`, `registerTime`, `recentLoginTime`, `loginCount`, `sex`, `icon`, `password`) VALUES
(1, '15974154924', '2016-12-31 12:31', '2016-12-25 16:33:28.186', 32, '男', '', '123456');

-- --------------------------------------------------------

--
-- 表的结构 `versioninfo`
--

CREATE TABLE IF NOT EXISTS `versioninfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `versionCode` int(11) NOT NULL,
  `versionName` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `downloadUrl` text NOT NULL,
  `time` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- 转存表中的数据 `versioninfo`
--

INSERT INTO `versioninfo` (`id`, `versionCode`, `versionName`, `content`, `downloadUrl`, `time`) VALUES
(3, 3, '3.0', '优化程序的运行效率,增加新功能哦', 'http://rookie-file.oss-cn-shanghai.aliyuncs.com/app-debug.apk', 'ad'),
(4, 7, '1.6', '规划大调整，暂时砍掉了图片管理模板和资料管理模块', 'http://rookie-file.oss-cn-shanghai.aliyuncs.com/version/app-release-1.6_signed.apk', '');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;





--
-- 表的结构 `mine_sysconst`
--

CREATE TABLE IF NOT EXISTS `mine_loginfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,

  `ip` varchar(20) DEFAULT NULL COMMENT 'ip',
  `url` varchar(20) DEFAULT NULL COMMENT '地址',
  `type` varchar(50) DEFAULT NULL COMMENT '类型',
  `method` varchar(20) DEFAULT NULL COMMENT '方法',
  `paramData` varchar(50) DEFAULT NULL COMMENT '参数',
    `sessionId` varchar(50) DEFAULT NULL COMMENT 'session',

  `time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
      `returnTime` varchar(50) DEFAULT NULL COMMENT '返回时间',
    `returnData` varchar(50) DEFAULT NULL COMMENT '返回数据',
    `httpStatusCode` varchar(50) DEFAULT NULL COMMENT '响应码',
    `timeConsuming` int(20) DEFAULT NULL COMMENT '耗时',

  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='日志记录表' AUTO_INCREMENT=1 ;
 
 
 
 --
-- 表的结构 `mine_siteinformation`
--

CREATE TABLE IF NOT EXISTS `mine_siteinformation` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
  `site_create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `remarks` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='建站信息' AUTO_INCREMENT=1 ;
 	 
INSERT INTO `mine_siteinformation` (`remarks`) VALUES
('站点信息'),