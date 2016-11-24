xquery version "3.0";

declare variable $docClean external;
declare variable $doc external;
declare variable $searchString external;

<quran>
{
    for $chapter in $docClean/quran/sura
    let $chapterNumber := $chapter/@index
    return <sura index="{$chapterNumber}" name="{$chapter/@name}">
        {
            for $verseClean in $docClean/quran/sura[@index=$chapterNumber]/aya[contains(@text, $searchString)]
            let $verseNumber := $verseClean/@index
            return
                for $verse in $doc/quran/sura[@index=$chapterNumber]/aya[@index=$verseNumber]
                return $verse
        }
    </sura>
}
</quran>
