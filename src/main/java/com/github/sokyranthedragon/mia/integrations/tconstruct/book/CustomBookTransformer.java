package com.github.sokyranthedragon.mia.integrations.tconstruct.book;

import slimeknights.mantle.client.book.BookTransformer;
import slimeknights.mantle.client.book.data.BookData;
import slimeknights.mantle.client.book.data.PageData;
import slimeknights.mantle.client.book.data.SectionData;
import slimeknights.mantle.client.book.data.content.PageContent;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.book.content.ContentListing;
import slimeknights.tconstruct.library.book.content.ContentModifier;
import slimeknights.tconstruct.library.modifiers.IModifier;

import java.util.List;

// This class was taken from EnderIO
// Source: https://github.com/SleepyTrousers/EnderIO/blob/master/enderio-integration-tic/src/main/java/crazypants/enderio/integration/tic/book/OurBookTransformer.java
// It was slightly modified (to match this mod), but it's mostly the same class.

class CustomBookTransformer extends BookTransformer
{
    private List<String> modifiersToAdd;
    
    public CustomBookTransformer(List<String> modifiersToAdd)
    {
        this.modifiersToAdd = modifiersToAdd;
    }
    
    @Override
    public void transform(BookData book)
    {
        SectionData section1 = null;
        SectionData section2 = null;
        
        for (SectionData section : book.sections)
        {
            if (section.name.equals("modifiers"))
                section1 = section;
            else if (section.name.equals("mia_modifiers"))
                section2 = section;
        }
        
        if (section1 != null && section2 != null)
        {
            for (PageData page : section2.pages)
            {
                if (modifiersToAdd.contains(((ContentModifier) page.content).modifierName))
                {
                    page.parent = section1;
                    section1.pages.add(page);
                }
            }
            
            PageData pageData = section1.pages.get(0);
            PageContent content = pageData.content;
            
            if (content instanceof ContentListing)
            {
                ContentListing listing = (ContentListing) content;
                
                for (PageData page : section2.pages)
                {
                    page.parent = section1;
                    
                    if (page.content instanceof ContentModifier)
                    {
                        IModifier modifier = TinkerRegistry.getModifier(((ContentModifier) page.content).modifierName);
                        
                        if (modifier != null)
                        {
                            page.name = "page-mia-" + modifier.getIdentifier();
                            listing.addEntry(modifier.getLocalizedName(), page);
                        }
                    }
                }
            }
            
            section2.pages.clear();
            book.sections.remove(section2);
        }
    }
}