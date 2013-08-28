/**
 * @license Copyright (c) 2003-2013, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function(config) {
    // Define changes to default configuration here.
    // For the complete reference:
    // http://docs.ckeditor.com/#!/api/CKEDITOR.config

    // The toolbar groups arrangement, optimized for two toolbar rows.

    /*
     config.toolbarGroups = [
     { name: 'clipboard',   groups: [ 'clipboard', 'undo' ] },
     { name: 'editing',     groups: [ 'find', 'selection', 'spellchecker' ] },
     { name: 'links' },
     { name: 'insert' },
     { name: 'forms' },
     { name: 'tools' },
     { name: 'document',	   groups: [ 'mode', 'document', 'doctools' ] },
     { name: 'others' },
     '/',
     { name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
     { name: 'paragraph',   groups: [ 'list', 'indent', 'blocks', 'align', 'bidi' ] },
     { name: 'styles' },
     { name: 'colors' },
     { name: 'about' }
     ]; */



    config.toolbar = [
        { name: 'styles', items : [ 'Styles', 'Font'] },
        { name: 'basicstyles', groups: [ 'basicstyles'], items: [ 'Bold', 'Italic'] },
        { name: 'clipboard', groups: [ 'clipboard', 'undo' ], items: [ 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'Undo', 'Redo' ] },
        { name: 'paragraph', groups: [ 'list',  'blocks'], items: [ 'NumberedList', 'BulletedList', '-', 'Blockquote' ] },
        { name: 'links', items: [ 'Link', 'Unlink'] },
        { name: 'insert', items: [ 'Image', 'SpecialChar' ] },
        { name: 'tools', items: [ 'Maximize' ] }

    ];

    config.font_names =
        'Arial/Arial, Helvetica, sans-serif;' +
            'Times New Roman/Times New Roman, Times, serif;' +
            'Verdana';

    CKEDITOR.stylesSet.add( 'produx_styles', [
        { name: 'Normaal', element: 'p'},
        { name: 'Kopje', element: 'h2'},
        { name: 'Subkopje' , element: 'h3'},
        { name: 'Code block', element: 'pre' },
        { name: 'Code inline', element: 'span', attributes: { 'style':'font-family: Courier; font-size: 1.2em'} }
    ]);



    config.stylesSet = 'produx_styles';


    // Remove some buttons, provided by the standard plugins, which we don't
    // need to have in the Standard(s) toolbar.
    config.removeButtons = 'Underline,Subscript,Superscript';

    // Se the most common block elements.
    config.format_tags = 'p;h1;h2;h3;pre';

    // Make dialogs simpler.
    config.removeDialogTabs = 'image:advanced;link:advanced';

    // Change color
    config.uiColor = '#d4d0d0';

    config.filebrowserBrowseUrl = '/cbckfilemanager/browse';
    config.filebrowserUploadUrl = '/cbckfilemanager/upload';
    // config.height = '800px'; Doesn't work?
};
