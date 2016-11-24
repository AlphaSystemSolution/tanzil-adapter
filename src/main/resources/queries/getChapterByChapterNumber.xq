xquery version "3.0";

declare variable $doc external;
declare variable $chapterNumber external;

<quran>{$doc/quran/sura[@index=$chapterNumber ]}</quran>