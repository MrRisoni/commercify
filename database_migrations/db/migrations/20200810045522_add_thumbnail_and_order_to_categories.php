<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class AddThumbnailAndOrderToCategories extends AbstractMigration
{

    public function change(): void
    {
        $table = $this->table('shop_belongs_categories');
        $table->addColumn('thumbnail_url', 'string')->update();
        $table->addColumn('show_order', 'integer')->update();

    }
}
