<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class NewTableProductGalleryTags extends AbstractMigration
{
    /**
     * Change Method.
     *
     * Write your reversible migrations using this method.
     *
     * More information on writing migrations is available here:
     * https://book.cakephp.org/phinx/0/en/migrations.html#the-change-method
     *
     * Remember to call "create()" or "update()" and NOT "save()" when working
     * with the Table class.
     */
    public function change(): void
    {
        $productGalleryTag= $this->table('product_gallery_tag', ['signed' => false]);
        $productGalleryTag->addColumn('image_id', 'biginteger', ['signed' => false])
            ->addColumn('language_id', 'biginteger', ['signed' => false])
            ->addColumn('tag', 'string', ['limit' => 55])
            ->addForeignKey('image_id', 'product_gallery', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
            ->addForeignKey('language_id', 'languages', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
            ->create();
    }
}
