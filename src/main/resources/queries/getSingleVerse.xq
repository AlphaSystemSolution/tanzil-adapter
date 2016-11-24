xquery version "3.0";

declare variable $doc external;
declare variable $verseNumber external;
declare variable $chapterNumber external;

<quran>
<sura index="{$chapterNumber}" name="{$doc/quran/sura[@index=$chapterNumber]/@name}">
{
$doc/quran/sura[@index=$chapterNumber]/aya[@index = $verseNumber]
}
</sura>
</quran>